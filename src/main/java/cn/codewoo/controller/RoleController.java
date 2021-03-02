package cn.codewoo.controller;

import cn.codewoo.aop.annotation.MyLog;
import cn.codewoo.entity.SysPermission;
import cn.codewoo.entity.SysRole;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.service.IRoleService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.RoleAddReqVo;
import cn.codewoo.vo.req.RoleEditReqVO;
import cn.codewoo.vo.req.RolePageReqVO;
import cn.codewoo.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author KehongWu
 */
@Api(tags = "组织管理-角色管理相关接口")
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    IRoleService roleService;
    @Autowired
    IPermissionService permissionService;
    @ApiOperation(value = "分页查询角色信息")
    @PostMapping("/auth/role/page")
    @MyLog(title = "角色管理",action = "分页查询角色信息")
    //@RequiresPermissions("sys:role:list")
    public DataResult<PageRespVO<SysRole>> pageInfo(@RequestBody RolePageReqVO vo){
        DataResult result = DataResult.success(roleService.pageInfo(vo));
        return result;
    }

    @ApiOperation("新增角色信息")
    @PostMapping("/auth/v2/role/add")
    @MyLog(title = "角色管理",action = "新增角色")
    public DataResult<SysRole> addRole(@RequestBody @Valid RoleAddReqVo vo){
        return DataResult.success(roleService.addRole(vo));
    }

    @ApiOperation("获取用户拥有的角色")
    @GetMapping("/auth/role/user/list")
    @MyLog(title = "角色管理",action = "获取用户拥有的角色列表")
    //@RequiresPermissions("sys:role:list")
    public DataResult<List<SysRole>> userRoleList(@RequestParam String userId){
        List<SysRole> sysRoles = roleService.selectUserRoleByUserId(userId);
        return DataResult.success(sysRoles);
    }

    @ApiOperation("全部角色列表")
    @GetMapping("/auth/role/list")
    @MyLog(title = "角色管理",action = "获取全部角色列表")
    //@RequiresPermissions("sys:role:list")
    public DataResult<List<SysRole>> roleList(){
        List<SysRole> sysRoles = roleService.selectAll();
        return DataResult.success(sysRoles);
    }

    @ApiOperation("获取角色拥有的权限，不封装tree")
    @GetMapping("/auth/role/permission/list")
    @MyLog(title = "角色管理",action = "获取角色拥有权限")
    //@RequiresPermissions({"sys:role:list","sys:permission:list"})
    public DataResult<List<SysPermission>> rolePermissionList(@RequestParam String roleId){
        List<SysPermission> tree = permissionService.selectRolePermissionList(roleId);
        return DataResult.success(tree);
    }

    @ApiOperation("编辑角色信息")
    @PostMapping("/auth/v2/role/edit")
    @MyLog(title = "角色管理",action = "编辑角色信息")
    //@RequiresPermissions("sys:role:edit")
    public DataResult roleEdit(@RequestBody RoleEditReqVO vo){
        int row = roleService.editRole(vo);
        if (row != 1){
            return DataResult.error();
        }else {
            
            return DataResult.success();
        }
    }

    @ApiOperation("根据角色id获取角色信息")
    @GetMapping("/auth/role/info/{id}")
    @MyLog(title = "角色管理",action = "获取角色基本信息")
    public DataResult<SysRole> roleInfo(@PathVariable String id){
        SysRole sysRole = roleService.selectRoleById(id);
        return DataResult.success(sysRole);
    }
}
