package cn.codewoo;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.service.RedisService;
import cn.codewoo.vo.resp.PermissionRespNodeVO;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class CompanyFrameApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private RedisService redisService;
    @Autowired
    private IPermissionService permissionService;
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



}
