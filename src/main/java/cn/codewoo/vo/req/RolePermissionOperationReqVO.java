package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author KehongWu
 * 角色与权限信息关联表VO
 */
@Data
public class RolePermissionOperationReqVO {
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("菜单权限集合")
    private List<String> permissionIds;
}
