package cn.codewoo.service.impl;

import cn.codewoo.entity.SysDept;
import cn.codewoo.entity.SysPermission;
import cn.codewoo.entity.SysUser;
import cn.codewoo.mapper.SysDeptMapper;
import cn.codewoo.mapper.SysUserMapper;
import cn.codewoo.service.IHomeService;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.vo.resp.HomeRespVO;
import cn.codewoo.vo.resp.PermissionRespNodeVO;
import cn.codewoo.vo.resp.UserInfoRespVO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kehong
 */
@Slf4j
@Service
public class HomeServiceImpl implements IHomeService {
    @Autowired(required = false)
    private SysUserMapper sysUserMapper;
    @Autowired
    private IPermissionService permissionService;

    @Autowired(required = false)
    private SysDeptMapper sysDeptMapper;

    /**
     * 获取首页展示的信息
     * @param userId 用户id
     * @return
     */
    @Override
    public HomeRespVO getHomeInfo(String userId) {
        List<SysPermission> permissions = new ArrayList<>();
        HomeRespVO homeRespVO = new HomeRespVO();
        //获取用户详情
        SysUser sysUser = sysUserMapper.selectDetailedById(userId);
        UserInfoRespVO userInfo = new UserInfoRespVO();
        userInfo.setId(sysUser.getId());
        userInfo.setDeptId(sysUser.getDeptId());
        //获取部门信息
        if (sysUser.getDeptId() != null){
            SysDept sysDept = sysDeptMapper.selectByPrimaryKey(sysUser.getDeptId());
            if (sysDept != null){
                userInfo.setDeptName(sysDept.getName());
            }
        }
        userInfo.setNickName(sysUser.getNickName());
        userInfo.setRealName(sysUser.getRealName());
        userInfo.setUsername(sysUser.getUsername());
        userInfo.setPhone(sysUser.getPhone());
        homeRespVO.setUserInfo(userInfo);
        //获取角色的权限列表
        sysUser.getSysRoleList().forEach(sysRole -> {
            sysRole.getSysPermissionList().forEach(sysPermission -> permissions.add(sysPermission));
        });
        //对权限去重
        List<SysPermission> collect = permissions.stream().distinct().collect(Collectors.toList());
        List<PermissionRespNodeVO> menuTree = permissionService.getMenuTree(collect);


        homeRespVO.setMenus(menuTree);
        return homeRespVO;
    }




}
