package cn.codewoo.shiro;

import cn.codewoo.constant.Constant;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.service.RedisService;
import cn.codewoo.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String accessToken = (String) info.getCredentials();
        if (accessToken == null) {
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_NOT_NULL);
        }
        //获取用户id
        String userId = JwtTokenUtil.getUserId(accessToken);
        if (userId == null) {
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_ERROR);
        }
        //用户是否被锁定
        if (redisService.hasKey(Constant.ACCOUNT_LOCK_KEY + userId)) {
            throw new BusinessException(BaseResponseCodeImpl.ACCOUNT_LOCK);
        }
        //用户是否被删除
        if (redisService.hasKey(Constant.DELETED_USER_KEY + userId)) {
            throw new BusinessException(BaseResponseCodeImpl.ACCOUNT_HAS_DELETE_ERROR);
        }
        //校验token
        if (!JwtTokenUtil.validateToken(accessToken)) {
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_ERROR);
        }
        //校验token是否被加入了黑名单
        String accessBlackListKey = Constant.JWT_ACCESS_TOKEN_BLACKLIST+accessToken;
        if (redisService.hasKey(Constant.JWT_ACCESS_TOKEN_BLACKLIST+accessToken)){
            throw new BusinessException(BaseResponseCodeImpl.TOKEN_ERROR);
        }
        /**
         * 判断用户是否被标记了
         */
        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)){
            /**
             * 判断用户是否已经刷新过
             */
            if(redisService.getExpire(Constant.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
                throw new BusinessException(BaseResponseCodeImpl.TOKEN_PAST_DUE);
            }
        }
        return true;
    }

}
