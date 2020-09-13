package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author kehong
 */
@Data
@Builder
public class UserInfoRespVO {
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("所属机构id")
    private String deptId;
    @ApiModelProperty("所属机构名称")
    private String deptName;
}
