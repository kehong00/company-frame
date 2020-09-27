package cn.codewoo.controller;

import cn.codewoo.entity.SysRole;
import cn.codewoo.service.IRoleService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.RoleAddReqVo;
import cn.codewoo.vo.req.RolePageReqVO;
import cn.codewoo.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author KehongWu
 */
@Api(tags = "组织管理-角色管理相关接口")
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    IRoleService roleService;
    @ApiOperation(value = "分页查询角色信息")
    @PostMapping("/roles")
    public DataResult<PageRespVO<SysRole>> pageInfo(@RequestBody RolePageReqVO vo){
        DataResult result = DataResult.success(roleService.pageInfo(vo));
        return result;
    }

    @ApiOperation("新增角色信息")
    @PostMapping("/role")
    public DataResult<SysRole> addRole(@RequestBody @Valid RoleAddReqVo vo){
        return DataResult.success(roleService.addRole(vo));
    }
}
