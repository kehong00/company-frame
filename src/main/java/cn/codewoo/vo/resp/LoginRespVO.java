package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModelProperty;


public class LoginRespVO {
    @ApiModelProperty(value = "业务token")
    private String accessToken;
    @ApiModelProperty(value = "刷新token")
    private String refreshToken;
    @ApiModelProperty(value = "用户id")
    private String userId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoginRespVO{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
