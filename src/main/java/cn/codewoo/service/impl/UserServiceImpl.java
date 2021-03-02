package cn.codewoo.service.impl;

import cn.codewoo.constant.Constant;
import cn.codewoo.entity.SysDept;
import cn.codewoo.entity.SysPermission;
import cn.codewoo.entity.SysRole;
import cn.codewoo.entity.SysUser;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.mapper.SysDeptMapper;
import cn.codewoo.mapper.SysUserMapper;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.service.IRoleService;
import cn.codewoo.service.IUserService;
import cn.codewoo.service.RedisService;
import cn.codewoo.utils.*;
import cn.codewoo.vo.req.LoginReqVO;
import cn.codewoo.vo.req.UserAddReqVO;
import cn.codewoo.vo.req.UserPageReqVO;
import cn.codewoo.vo.resp.LoginRespVO;
import cn.codewoo.vo.resp.PageRespVO;
import cn.codewoo.vo.resp.UserPersonalRespVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = false)
    SysUserMapper userMapper;

    @Autowired(required = false)
    SysDeptMapper deptMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private TokenSetting tokenSetting;

    @Autowired
    private RedisService redisService;

    @Override
    public LoginRespVO login(LoginReqVO vo) {
        SysUser sysUser = userMapper.selectByUsername(vo.getUsername());
        if (sysUser == null){
            throw new BusinessException(BaseResponseCodeImpl.ACCOUNT_NOTFOUND);
        }
        if (sysUser.getStatus() == 2){
            throw new BusinessException(BaseResponseCodeImpl.ACCOUNT_LOCK_TIP);
        }
        if (!PasswordUtils.matches(sysUser.getSalt(),vo.getPassword(),sysUser.getPassword())){
            throw new BusinessException(BaseResponseCodeImpl.ACCOUNT_PASSWORD_ERROR);
        }
        //业务token的负载集合
        Map<String,Object> claims = new HashMap<>();
        claims.put(Constant.JWT_USER_NAME,sysUser.getUsername());
        claims.put(Constant.ROLES_INFOS_KEY,getRoleByUserId(sysUser.getId()));
        claims.put(Constant.PERMISSIONS_INFOS_KEY,getPermissionByUserId(sysUser.getId()));
        String accountToken = JwtTokenUtil.getAccessToken(sysUser.getId(), claims);
        log.info("accessToken={}",accountToken);
        //刷新token的负载集合
        Map<String,Object> refreshClaims = new HashMap<>();
        refreshClaims.put(Constant.JWT_USER_NAME,sysUser.getUsername());
        String refreshToken = null;
        if (vo.getType().equals(1)){
            refreshToken = JwtTokenUtil.getRefreshToken(sysUser.getId(),refreshClaims);
        }else {
            refreshToken = JwtTokenUtil.getRefreshAppToken(sysUser.getId(),refreshClaims);
        }
        log.info("refreshToken={}", refreshToken);
        LoginRespVO respVO = new LoginRespVO();
//        return LoginRespVO.builder().accessToken(accountToken).refreshToken(refreshToken).userId(sysUser.getId()).build();
        respVO.setAccessToken(accountToken);
        respVO.setRefreshToken(accountToken);
        respVO.setUserId(sysUser.getId());
        return respVO;
    }

    @Override
    public PageRespVO<SysUser> userPageInfo(UserPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysUser> sysUsers = userMapper.userQuery(vo);
//        List<SysUser> sysUsers = userMapper.selectPageInfo();
        log.info("sysUsers instanceof Page = {}",sysUsers instanceof Page);
        PageRespVO<SysUser> pageRespVO = PageUtil.getPageRespVO(sysUsers);
        return pageRespVO;
    }

    @Override
    public SysUser selectDetailedById(String userId) {
        return userMapper.selectDetailedById(userId);
    }

    @Override
    public int register(UserAddReqVO vo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(vo,sysUser);
        //获取所属部门信息
        SysDept sysDept = deptMapper.selectByPrimaryKey(sysUser.getDeptId());
        if (sysDept == null){
            //不能获取部门信息，抛出异常
            throw new BusinessException(BaseResponseCodeImpl.DEPT_NOT_FOUNT_ERROR);
        }
        String salt = CommonUtils.generateUUID().substring(0,20);
        String encodePwd = PasswordUtils.encode(sysUser.getPassword(), salt);
        sysUser.setPassword(encodePwd);
        sysUser.setSalt(salt);
        sysUser.setCreateTime(new Date());
        sysUser.setId(CommonUtils.generateUUID());

        int row = userMapper.insertSelective(sysUser);

        return row;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,BusinessException.class})

    public int deleteUserById(String id) {
        int row = userMapper.updateDeleteById(id);
        if (row != 1){
            throw new BusinessException(BaseResponseCodeImpl.SYS_ERROR);
        }
        return row;
    }

    @Override
    public String refreshToken(String refreshToken) {
        if (!JwtTokenUtil.validateToken(refreshToken)){
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_ERROR);
        }
        String userId = JwtTokenUtil.getUserId(refreshToken);
        String username = JwtTokenUtil.getUserName(refreshToken);
        Map<String,Object> cliams = new HashMap<>();
        cliams.put(Constant.JWT_USER_NAME,username);
        cliams.put(Constant.ROLES_INFOS_KEY,getRoleByUserId(userId));
        cliams.put(Constant.PERMISSIONS_INFOS_KEY,getPermissionByUserId(userId));
        String newAccessToken = JwtTokenUtil.getAccessToken(userId,cliams);
        return newAccessToken;
    }

    @Override
    public int loginOut(String accessToken, String refreshToken) {

        try{
            if (!StringUtils.hasLength(accessToken) || !StringUtils.hasLength(refreshToken)){
                throw new BusinessException(BaseResponseCodeImpl.DATA_ERROR);
            }
            //登场shiro
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()){
                subject.logout();
            }
            //将token加入黑名单
            String userId = JwtTokenUtil.getUserId(accessToken);
            redisService.set(Constant.JWT_ACCESS_TOKEN_BLACKLIST+accessToken,userId != null?userId:"val",
                    tokenSetting.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
            //将refreshToken加入白名单
            redisService.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken,userId != null?userId:"val",
                    JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.error("发生异常：",e);
        }
        return 0;
    }

    @Override
    public UserPersonalRespVO getUserInfo(String userId) {
        //获取用户记录
        SysUser sysUser = userMapper.selectByPrimaryKey(userId);
        UserPersonalRespVO vo = new UserPersonalRespVO();
        BeanUtils.copyProperties(sysUser,vo);
        //获取部门记录
        String deptId = sysUser.getDeptId();
        if (StringUtils.hasLength(deptId)){
            SysDept sysDept = deptMapper.selectByPrimaryKey(deptId);
            if (sysDept != null){
                vo.setDeptName(sysDept.getName());
            }
        }
        return vo;
    }


    /**
     * 获取用户拥有的角色
     * @param id
     * @return
     */
    @Override
    public List<String> getRoleByUserId(String id){
        List<SysRole> sysRoles = roleService.selectUserRoleByUserId(id);
        List<String> list = new ArrayList<>();
        sysRoles.forEach(role -> list.add(role.getName()));
        return list;
    }

    /**
     * 获取用户拥有的权限
     * @param id
     * @return
     */
    @Override
    public List<String> getPermissionByUserId(String id){
        List<SysRole> sysRoles = roleService.selectUserRoleByUserId(id);
        List<String> list = new ArrayList<>();
        sysRoles.forEach(role -> {
            List<SysPermission> permissions = permissionService.selectRolePermissionList(role.getId());
            permissions.forEach(sysPermission -> {
                if (StringUtils.hasLength(sysPermission.getPerms())){
                    list.add(sysPermission.getPerms());
                }

            });
        });
        return list;
    }

}
