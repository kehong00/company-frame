package cn.codewoo.service.impl;

import cn.codewoo.constant.Constant;
import cn.codewoo.entity.SysUser;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.mapper.SysUserMapper;
import cn.codewoo.service.IUserService;
import cn.codewoo.utils.JwtTokenUtil;
import cn.codewoo.utils.PageUtil;
import cn.codewoo.utils.PasswordUtils;
import cn.codewoo.vo.req.LoginReqVO;
import cn.codewoo.vo.req.UserPageReqVO;
import cn.codewoo.vo.resp.LoginRespVO;
import cn.codewoo.vo.resp.PageRespVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    SysUserMapper userMapper;
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
        return LoginRespVO.builder().accessToken(accountToken).refreshToken(refreshToken).userId(sysUser.getId()).build();
    }

    @Override
    public PageRespVO<SysUser> pageInfo(UserPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysUser> sysUsers = userMapper.selectAll();
        log.info("sysUsers instanceof Page = {}",sysUsers instanceof Page);
        PageRespVO<SysUser> pageRespVO = PageUtil.getPageRespVO(sysUsers);
        return pageRespVO;
    }

    private List<String> getRoleByUserId(String id){
        List<String> list = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(id)){
            list.add("admin");
        }else {
            list.add("test");
        }
        return list;
    }

    private List<String> getPermissionByUserId(String id){
        List<String> list = new ArrayList<>();
        if ("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(id)){
            list.add("sys:user:add");
            list.add("sys:user:delete");
            list.add("sys:user:update");
            list.add("sys:user:list");
        }
        return list;
    }

}
