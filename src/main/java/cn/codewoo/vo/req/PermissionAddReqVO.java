package cn.codewoo.vo.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author KehongWu
 */
@Data
@ApiModel(value = "添加菜单权限请求vo")
public class PermissionAddReqVO {
    @ApiModelProperty(value = "菜单权限名称")
    @NotNull(message = "菜单权限名称不能为空")
    private String name;

    @ApiModelProperty("菜单权限标识，适配restful")
    private String perms;

    @ApiModelProperty("菜单父级地址")
    @NotNull(message = "父级id不能为空")
    private String pid;

    @ApiModelProperty("菜单权限请求地址")
    private String url;

    @ApiModelProperty("菜单权限请求方式")
    private String method;

    @ApiModelProperty("权限类型")
    private Integer type;

    @ApiModelProperty("菜单排序码")
    private Integer orderNum;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("编码")
    private String code;
}
