package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleAddReqVo {
    @ApiModelProperty("角色名称")
    @NotNull(message = "角色名称不能为空")
    private String name;
    @ApiModelProperty("描述信息")
    private String description;
    @ApiModelProperty("状态（1：正常、2：禁用）")
    private Integer status;
    @ApiModelProperty("权限列表")
    private List<String> permissionIds;
}
