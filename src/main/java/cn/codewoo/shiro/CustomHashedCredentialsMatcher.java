package cn.codewoo.shiro;

import cn.codewoo.constant.Constant;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.service.RedisService;
import cn.codewoo.utils.JwtTokenUtil;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userToken = (String) info.getCredentials();
        if (userToken == null) {
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_NOT_NULL);
        }
        //获取用户id
        String userId = JwtTokenUtil.getUserId(userToken);
        if (userId == null) {
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_ERROR);
        }
        if (redisService.hasKey(Constant.ACCOUNT_LOCK_KEY + userId)) {
            throw new BusinessException(BaseResponseCodeImpl.ACCOUNT_LOCK);
        }
        if (redisService.hasKey(Constant.DELETED_USER_KEY + userId)) {
            throw new BusinessException(BaseResponseCodeImpl.ACCOUNT_HAS_DELETE_ERROR);
        }
        if (!JwtTokenUtil.validateToken(userToken)) {
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_ERROR);
        }
        return true;
    }

}
