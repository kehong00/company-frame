package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author KehongWu
 * 角色与权限信息关联表VO
 */
public class RolePermissionOperationReqVO {
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("菜单权限集合")
    private List<String> permissionIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "RolePermissionOperationReqVO{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", permissionIds=" + permissionIds +
                '}';
    }
}
