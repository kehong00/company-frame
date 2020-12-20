package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName RoleEditReqVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/17 8:39 上午
 * @Version 1.0
 **/
public class RoleEditReqVO {
    @ApiModelProperty("角色id")
    @NotNull(message = "角色id不能为空")
    private String id;
    @ApiModelProperty("角色名称")
    @NotNull(message = "角色名称不能为空")
    private String name;
    @ApiModelProperty("描述信息")
    private String description;
    @ApiModelProperty("状态（1：正常、2：禁用）")
    private Integer status;
    @ApiModelProperty("权限列表")
    private List<String> permissionIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "RoleEditReqVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", permissionIds=" + permissionIds +
                '}';
    }
}