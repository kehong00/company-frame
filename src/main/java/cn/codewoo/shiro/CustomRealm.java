package cn.codewoo.shiro;

import cn.codewoo.service.IUserService;
import cn.codewoo.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;
    //获取角色、权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = (String) principals.getPrimaryPrincipal();
        String userId = JwtTokenUtil.getUserId(token);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(userService.getRoleByUserId(userId));
        info.addStringPermissions(userService.getPermissionByUserId(userId));
        return info;
    }

    //获取凭证携带的身份信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomUsernamePasswordToken CustomUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        return new SimpleAuthenticationInfo(CustomUsernamePasswordToken.getPrincipal(), CustomUsernamePasswordToken.getCredentials(), this.getName());
    }


}
