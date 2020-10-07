package cn.codewoo.controller;

import cn.codewoo.entity.SysDept;
import cn.codewoo.service.IDeptService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.resp.DeptRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author kehong
 */
@RestController
@RequestMapping("/api")
@Api(tags = "部门管理")
public class DeptController {
    @Autowired
    private IDeptService deptService;
    @GetMapping("/depts")
    @ApiOperation("查询所有部门数据接口")
    public DataResult<List<SysDept>> getAllDept(){
        return DataResult.success(deptService.selectAll());
    }

    @GetMapping("/dept/tree")
    public DataResult<List<DeptRespNodeVO>> getDeptTree(){
        return DataResult.success(deptService.deptTreeList());
    }
}
