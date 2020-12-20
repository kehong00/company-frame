package cn.codewoo.vo.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author KehongWu
 */

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PermissionAddReqVO{" +
                "name='" + name + '\'' +
                ", perms='" + perms + '\'' +
                ", pid='" + pid + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", type=" + type +
                ", orderNum=" + orderNum +
                ", status=" + status +
                ", code='" + code + '\'' +
                '}';
    }
}
