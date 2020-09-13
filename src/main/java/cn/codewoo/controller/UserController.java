package cn.codewoo.controller;

import cn.codewoo.entity.SysUser;
import cn.codewoo.service.IUserService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.LoginReqVO;
import cn.codewoo.vo.req.UserPageReqVO;
import cn.codewoo.vo.resp.LoginRespVO;
import cn.codewoo.vo.resp.PageRespVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = "用户模块相关接口")
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo){
        return DataResult.success(userService.login(vo));
    }

    @PostMapping("/users")
    @ApiOperation("获取所有用户记录")
    @RequiresPermissions("test")
    public DataResult getUsers(@RequestBody UserPageReqVO vo){
        PageRespVO<SysUser> sysUserPageRespVO = userService.pageInfo(vo);
        return DataResult.success(sysUserPageRespVO);
    }


}
