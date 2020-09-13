package cn.codewoo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializerUtil {
    @Autowired
    public InitializerUtil(TokenSetting tokenSetting) {
        JwtTokenUtil.setJwtProperties(tokenSetting);
    }
}
