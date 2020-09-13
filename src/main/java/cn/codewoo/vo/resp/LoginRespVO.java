package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRespVO {
    @ApiModelProperty(value = "业务token")
    private String accessToken;
    @ApiModelProperty(value = "刷新token")
    private String refreshToken;
    @ApiModelProperty(value = "用户id")
    private String userId;
}
