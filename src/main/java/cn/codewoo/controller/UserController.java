package cn.codewoo.controller;

import cn.codewoo.aop.annotation.MyLog;
import cn.codewoo.entity.SysRole;
import cn.codewoo.entity.SysUser;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.service.IRoleService;
import cn.codewoo.service.IUserRoleService;
import cn.codewoo.service.IUserService;
import cn.codewoo.utils.CommonUtils;
import cn.codewoo.utils.DataResult;
import cn.codewoo.utils.JwtTokenUtil;
import cn.codewoo.vo.req.*;
import cn.codewoo.vo.resp.LoginRespVO;
import cn.codewoo.vo.resp.PageRespVO;
import cn.codewoo.vo.resp.UserOwnRoleRespVO;
import cn.codewoo.vo.resp.UserPersonalRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "用户模块相关接口")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @PostMapping("/pub/user/login")
    @ApiOperation(value = "用户登录接口")
    @MyLog(title = "用户模块",action = "用户登录")
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo) {
        return DataResult.success(userService.login(vo));
    }

    @PostMapping("/auth/v1/user/list")
    @ApiOperation("获取所有用户记录")
//    @RequiresPermissions("test")
    @MyLog(title = "用户模块",action = "获取所有用户列表")
    public DataResult getUsers(@RequestBody UserPageReqVO vo) {
        PageRespVO<SysUser> sysUserPageRespVO = userService.userPageInfo(vo);
        return DataResult.success(sysUserPageRespVO);
    }

    @PostMapping("/auth/v2/user/add")
    @MyLog(title = "用户模块",action = "添加用户")
    public DataResult userAdd(@RequestBody UserAddReqVO vo) {
        int row = userService.register(vo);
        if (row != 1) {
            return DataResult.error();
        } else {
            return DataResult.success();
        }
    }

    @PostMapping("/auth/v2/user/delete")
    @ApiOperation("批量删除用户")
    @MyLog(title = "用户模块",action = "删除用户")
    public DataResult userDelete(@RequestBody UserDeleteReqVO vo) {
        vo.getUserIds().forEach(id -> userService.deleteUserById(id));
        return DataResult.success();
    }

    @GetMapping("/user/role/{userId}")
    @ApiOperation("获取用户拥有的和可赋予的角色")
    @MyLog(title = "用户模块",action = "获取用户角色")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable String userId) {
        UserOwnRoleRespVO vo = new UserOwnRoleRespVO();
        //获取用户拥有的角色
        List<SysRole> userRoles = roleService.selectUserRoleByUserId(userId);
        //获取全部角色列表
        List<SysRole> roles = roleService.selectAll();
        vo.setUserRoleList(userRoles);
        vo.setRoleList(roles);
        return DataResult.success(vo);
    }

    @PostMapping("/user/role/edit")
    @ApiOperation("编辑用户拥有角色信息")
    @MyLog(title = "用户模块",action = "编辑用户角色")
    public DataResult userOwnRoleEdit(@RequestBody @Validated UserOwnRoleEditReqVO vo) {
        int row = userRoleService.editOwnRole(vo);
        return DataResult.success();
    }

    @ApiOperation("使用refreshToken交换accessToken")
    @GetMapping("/pub/refreshtoken")
    @MyLog(title = "用户模块",action = "交换token")
    public DataResult<String> refreshToken(String refreshToken) throws UnsupportedEncodingException {
        if (!StringUtils.hasLength(refreshToken)){
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_ERROR);
        }
//        Base64.Decoder decoder = Base64.getDecoder();
//        decoder.decode(refreshToken.getBytes("UTF-8"));
        String accessToken = userService.refreshToken(refreshToken);
        return DataResult.success(accessToken);
    }

    @ApiOperation("退出登录")
    @GetMapping("/auth/loginout")
    @MyLog(title = "用户模块",action = "退出登录")
    public DataResult<String> loginOut(@RequestParam(name = "token") String accessToken,@RequestParam String refreshToken){
        int i = userService.loginOut(accessToken, refreshToken);
        return DataResult.success();
    }

    @ApiOperation("个人中心，个人信息")
    @GetMapping("/auth/user/personal")
    @MyLog(title = "用户模块",action = "个人信息")
    public DataResult<UserPersonalRespVO> personal(HttpServletRequest request){
        String token = CommonUtils.getToken(request);
        String userId = JwtTokenUtil.getUserId(token);
        UserPersonalRespVO userInfo = userService.getUserInfo(userId);
        return DataResult.success(userInfo);
    }

}
