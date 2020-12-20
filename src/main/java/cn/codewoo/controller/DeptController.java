package cn.codewoo.controller;

import cn.codewoo.aop.annotation.MyLog;
import cn.codewoo.entity.SysDept;
import cn.codewoo.service.IDeptService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.DeptEditReqVO;
import cn.codewoo.vo.req.SysDeptAddReqVO;
import cn.codewoo.vo.resp.DeptRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/auth/dept/list")
    @ApiOperation("查询所有部门数据接口")
    @MyLog(title = "部门管理",action = "获取全部部门列表")
    public DataResult<List<SysDept>> getAllDept(){
        return DataResult.success(deptService.selectAll());
    }

    @GetMapping("/auth/dept/tree")
    @ApiOperation("获取部门树形列表")
    @MyLog(title = "部门管理",action = "获取部门树形列表")
    public DataResult<List<DeptRespNodeVO>> getDeptTree(){
        return DataResult.success(deptService.deptTreeList());
    }

    @PostMapping("/auth/v2/dept/add")
    @ApiOperation("添加部门信息")
    @MyLog(title = "部门管理",action = "添加部门")
    public DataResult deptAdd(@RequestBody SysDeptAddReqVO vo){
        int row = deptService.addDept(vo);
        if (row != 1) {
            return DataResult.error();
        }else {
            return DataResult.success();
        }

    }

    @GetMapping("/auth/dept/info")
    @ApiOperation("获取部门信息")
    @MyLog(title = "部门管理",action = "获取部门基本信息")
    public DataResult<SysDept> deptInfo(@RequestParam String id){
        SysDept sysDept = deptService.selectById(id);
        return DataResult.success(sysDept);
    }

    @PostMapping("/auth/v2/dept/edit")
    @ApiOperation("编辑部门信息")
    @MyLog(title = "部门管理",action = "编辑部门信息")
    public DataResult deptEdit(@RequestBody DeptEditReqVO vo){
        int row = deptService.editDept(vo);
        if (row != 1){
            return DataResult.error();
        }else {
            return DataResult.success();
        }
    }
}
