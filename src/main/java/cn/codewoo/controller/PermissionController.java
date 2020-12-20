package cn.codewoo.controller;

import cn.codewoo.aop.annotation.MyLog;
import cn.codewoo.entity.SysPermission;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.PermissionAddReqVO;
import cn.codewoo.vo.req.SysPermissionEditReqVO;
import cn.codewoo.vo.resp.PermissionRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kehong
 */
@RestController
@RequestMapping("/api")
@Api(tags = "菜单管理模块")
public class PermissionController {
    @Autowired
    IPermissionService permissionService;
    @GetMapping("/auth/permission/list")
    @ApiOperation(value = "获取所有权限,不封装树")
    @MyLog(title = "权限管理",action = "获取所有权限列表")
    public DataResult<List<SysPermission>> getAllMenuPermission(){
        List<SysPermission> result = permissionService.getAllPermission();
        return DataResult.success(result);
    }


    @GetMapping("/auth/permission/tree")
    @ApiOperation("获取权限树数据，排除按钮")
    @MyLog(title = "权限管理",action = "获取权限树形列表，排除按钮")
    public DataResult<List<PermissionRespNodeVO>> selectAllMenuByTree(){
        return DataResult.success(permissionService.selectAllMenuByTreeExBtn());
    }

    @GetMapping("/auth/permission/tree/all")
    @ApiOperation("获取菜单权限树，不排除按钮")
    @MyLog(title = "权限管理",action = "获取权限树形列表，不排除按钮")
    public DataResult<List<PermissionRespNodeVO>> selectAllPermissionTree(){
        return DataResult.success(permissionService.selectAllPermissionTree());
    }

    @PostMapping("/auth/permission/add")
    @ApiOperation(value = "添加菜单权限记录")
    @MyLog(title = "权限管理",action = "添加权限记录")
    public DataResult<SysPermission> addPermission(@RequestBody PermissionAddReqVO vo){
        SysPermission sysPermission = permissionService.addPermission(vo);
        return DataResult.success(sysPermission);
    }

    @GetMapping("/pub/permission/info")
    @ApiOperation("根据权限id获取记录")
    @MyLog(title = "权限管理",action = "获取权限基本信息")
    public DataResult<SysPermission> permissionInfo(@RequestParam String id){
        SysPermission sysPermission = permissionService.selectById(id);
        return DataResult.success(sysPermission);
    }

    @PostMapping("/auth/v2/permission/edit")
    @ApiOperation("编辑菜单")
    @MyLog(title = "权限管理",action = "编辑权限信息")
    public DataResult permissionEdit(@RequestBody SysPermissionEditReqVO vo){
        int row = permissionService.updatePermissionById(vo);
        if (row != 1){
            return DataResult.error();
        }else {
            return DataResult.success();
        }
    }

    @DeleteMapping("/auth/v2/permission/del/{id}")
    @ApiOperation("删除菜单权限")
    @MyLog(title = "权限管理",action = "删除权限记录")
    public DataResult permissionDel(@PathVariable String id){
        int row = permissionService.deleteById(id);
        if (row != 1){
            return DataResult.error();
        }else{
            return DataResult.success();
        }
    }
}
