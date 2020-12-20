package cn.codewoo;

import cn.codewoo.constant.Constant;
import cn.codewoo.entity.SysDept;
import cn.codewoo.entity.SysPermission;
import cn.codewoo.entity.SysRolePermission;
import cn.codewoo.service.IDeptService;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.service.IRolePermissionService;
import cn.codewoo.service.RedisService;
import cn.codewoo.utils.JwtTokenUtil;
import cn.codewoo.utils.TokenSetting;
import cn.codewoo.vo.req.RolePermissionOperationReqVO;
import cn.codewoo.vo.resp.PermissionRespNodeVO;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CompanyFrameApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private RedisService redisService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IDeptService deptService;

    @Autowired
    private TokenSetting tokenSetting;

    @Autowired
    private IRolePermissionService rolePermissionService;
    @Test
    void contextLoads() {
    }

    @Test
    void jdbc_connection_test(){
        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void redis_test(){
        redisService.set("redisTest","test");
        System.out.println(redisService.get("redisTest"));
    }

    @Test
    void PermissionRespNode_test(){
        String homes = "[{\"children\":[{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"id\":\"6\",\"title\":\"五级类目5-6\",\"url\":\"string\"}],\"id\":\"5\",\"title\":\"四级类目4-5\",\"url\":\"string\"}],\"id\":\"4\",\"title\":\"三级类目3- 4\",\"url\":\"string\"}],\"id\":\"3\",\"title\":\"二级类目2- 3\",\"url\":\"string\"}],\"id\":\"1\",\"title\":\"类目1\",\"url\":\"string\"},{\"children\": [],\"id\":\"2\",\"title\":\"类目2\",\"url\":\"string\"}]";
        List<PermissionRespNodeVO> permissionRespNodeVOS = JSON.parseArray(homes, PermissionRespNodeVO.class);
        System.out.println(permissionRespNodeVOS);
    }

    @Test
    void PermissionService_test(){
        List<SysPermission> allPermission = permissionService.getAllPermission();
        System.out.println(allPermission);
    }

    @Test
    void role_permission_add_test(){
        List<String> list = new ArrayList<>();
        List<SysRolePermission> sysRolePermissions = new ArrayList<>();
        list.add("1f976592-b33c-4e21-8946-3a769a088fe2");
        list.add("86fbd727-728f-437c-a1ff-586758d2f588");
        for (String s : list) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setId(UUID.randomUUID().toString());
            sysRolePermission.setRoleId("10010011");
            sysRolePermission.setPermissionId(s);
            sysRolePermissions.add(sysRolePermission);
        }

        RolePermissionOperationReqVO vo = new RolePermissionOperationReqVO();
        vo.setRoleId("10010011");
        vo.setPermissionIds(list);
        rolePermissionService.addRolePermission(vo);
    }

    @Test
    void dept_select_all_test(){
        List<SysDept> sysDepts = deptService.selectAll();
        System.out.println(sysDepts);
    }

    @Test
    void refreshTTest(){
        redisService.set("testkey","testval",tokenSetting.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5YTI2ZjVmMS1jYmQyLTQ3M2QtODJkYi0xZDZkY2Y0NTk4ZjgiLCJyb2xlcy1pbmZvcy1rZXkiOlsiYWRtaW4iXSwicGVybWlzc2lvbnMtaW5mb3Mta2V5IjpbInN5czp1c2VyOmFkZCIsInN5czp1c2VyOmRlbGV0ZSIsInN5czp1c2VyOnVwZGF0ZSIsInN5czp1c2VyOmxpc3QiXSwiaXNzIjoiY29kZXdvby5jbiIsImp3dC11c2VyLW5hbWUta2V5IjoiYWRtaW4iLCJleHAiOjE2MDgyMTEzNDUsImlhdCI6MTYwODIwNDE0NX0._aLxOVCux7Zm1ZiHkdfyp-snXYngZZ7T2BgJiVD4afk";
        String userId = JwtTokenUtil.getUserId(token);
        String key = Constant.JWT_REFRESH_KEY + userId;
        String value = userId;
        redisService.set(key,value,tokenSetting.getAccessTokenExpireTime().toMillis(),TimeUnit.MILLISECONDS);
    }


}
