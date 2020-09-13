package cn.codewoo.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomUsernamePasswordToken extends UsernamePasswordToken {
    private String token;

    public CustomUsernamePasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
