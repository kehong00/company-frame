package cn.codewoo.shiro;

import cn.codewoo.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    //获取角色、权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = (String) principals.getPrimaryPrincipal();
        String userId = JwtTokenUtil.getUserId(token);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(getRoleByUserId(userId));
        info.addStringPermissions(getPermissionByUserId(userId));
        return info;
    }

    //获取凭证携带的身份信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomUsernamePasswordToken CustomUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        return new SimpleAuthenticationInfo(CustomUsernamePasswordToken.getPrincipal(), CustomUsernamePasswordToken.getCredentials(), this.getName());
    }

    private List<String> getRoleByUserId(String id){
        List<String> roles = new ArrayList<>();
        if (id.equals("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8")){
            roles.add("admin");
        }else{
            roles.add("test");
        }
        return roles;
    }

    private List<String> getPermissionByUserId(String id){
        List<String> permissions = new ArrayList<>();
        if(id.equals("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8")){
            permissions.add("*");
        }else {
            permissions.add("test:*");
        }
        return permissions;
    }

}
