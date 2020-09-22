package cn.codewoo.controller;

import cn.codewoo.entity.SysRole;
import cn.codewoo.service.IRoleService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.RolePageReqVO;
import cn.codewoo.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KehongWu
 */
@Api(tags = "组织管理-角色管理相关接口")
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    IRoleService roleService;
    @PostMapping("/roles")
    public DataResult<PageRespVO<SysRole>> pageInfo(@RequestBody RolePageReqVO vo){
        DataResult result = DataResult.success(roleService.pageInfo(vo));
        return result;
    }
}
