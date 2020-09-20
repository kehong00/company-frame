package cn.codewoo.service.impl;

import cn.codewoo.entity.SysUser;
import cn.codewoo.mapper.SysUserMapper;
import cn.codewoo.service.IHomeService;
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
    @Override
    public HomeRespVO getHomeInfo(String userId) {
        return HomeRespVO.builder()
                .userInfo(getUserInfoByUserId(userId))
                .menus(getPermissionRespNodeByUserId(userId)).build();
    }

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

    private List<PermissionRespNodeVO> getPermissionRespNodeByUserId(String userId){
//        String homes = "[{\"children\":[{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"id\":\"6\",\"title\":\"五级类目5-6\",\"\":\"/index/munus\"}],\"id\":\"5\",\"title\":\"四级类目4-5\",\"url\":\"/index/munus\"}],\"id\":\"4\",\"title\":\"三级类目3- 4\",\"url\":\"/index/munus\"}],\"id\":\"3\",\"title\":\"二级类目2- 3\",\"url\":\"/index/munus\"}],\"id\":\"1\",\"title\":\"类目1\",\"url\":\"/index/munus\"},{\"children\": [],\"id\":\"2\",\"title\":\"类目2\",\"url\":\"/index/munus\"}]";
        String home="[\n" +
                " {\n" +
                " \"children\": [\n" +
                " {\n" +
                " \"children\": [],\n" +
                " \"id\": \"3\",\n" +
                " \"title\": \"菜单权限管理\",\n" +
                " \"url\": \"/index/menus\"\n" +
                " }\n" +
                " ],\n" +
                " \"id\": \"1\",\n" +
                " \"title\": \"组织管理\",\n" +
                " \"url\": \"/index/menus\"\n" +
                " },\n" +
                " {\n" +
                " \"children\": [],\n" +
                " \"id\": \"2\",\n" +
                " \"title\": \"类目2\",\n" +
                " \"url\": \"/index/menus\"\n" +
                " }\n" +
                "]";
        List<PermissionRespNodeVO> permissionRespNodeVOS = JSON.parseArray(home, PermissionRespNodeVO.class);
        return permissionRespNodeVOS;
    }


}
