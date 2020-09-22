package cn.codewoo.controller;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.PermissionAddReqVO;
import cn.codewoo.vo.resp.PermissionRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
    @GetMapping("/permissions")
    @ApiOperation(value = "获取所有权限接口")
    public DataResult<List<SysPermission>> getAllMenuPermission(){
        List<SysPermission> result = permissionService.getAllPermission();
        return DataResult.success(result);
    }


    @GetMapping("/permission/tree")
    @ApiOperation("获取权限树数据")
    public DataResult<List<PermissionRespNodeVO>> selectAllMenuByTree(){
        return DataResult.success(permissionService.selectAllMenuByTree());
    }

    @PostMapping("/permission")
    @ApiOperation(value = "添加菜单权限记录")
    public DataResult<SysPermission> addPermission(@RequestBody PermissionAddReqVO vo){
        SysPermission sysPermission = permissionService.addPermission(vo);
        return DataResult.success(sysPermission);
    }
}
