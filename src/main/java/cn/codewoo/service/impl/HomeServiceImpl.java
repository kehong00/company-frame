package cn.codewoo.service.impl;

import cn.codewoo.entity.SysUser;
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

import java.util.List;

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

    /**
     * 获取首页展示的信息
     * @param userId 用户id
     * @return
     */
    @Override
    public HomeRespVO getHomeInfo(String userId) {
        return HomeRespVO.builder()
                .userInfo(getUserInfoByUserId(userId))
                .menus(getPermissionRespNodeByUserId(userId)).build();
    }

    /**
     * 封装用户信息
     * @param userId 用户id
     * @return
     */
    private UserInfoRespVO getUserInfoByUserId(String userId){
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        UserInfoRespVO userInfo = UserInfoRespVO.builder()
                .id(sysUser.getId())
                .username(sysUser.getUsername())
                .nickName(sysUser.getNickName())
                .phone(sysUser.getPhone())
                .realName(sysUser.getRealName())
                .deptId(sysUser.getDeptId())
                .deptName("xinhua").build();
        return userInfo;
    }

    /**
     * 获取菜单权限列表
     * @param userId
     * @return
     */
    private List<PermissionRespNodeVO> getPermissionRespNodeByUserId(String userId){
        List<PermissionRespNodeVO> permissionRespNodeVOS = permissionService.getPermissionTree(userId);
        return permissionRespNodeVOS;
    }


}
