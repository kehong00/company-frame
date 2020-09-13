package cn.codewoo.controller;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
