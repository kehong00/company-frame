package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author kehong
 */
public class PermissionRespNodeVO {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "菜单权限名称")
    private String name;
    @ApiModelProperty(value = "接口地址")
    private String url;

    private String code;
    private String perms;
    private String pid;
    private Integer orderNum;
    private Integer type;
    private Integer status;
    private Date createTime;

    @ApiModelProperty(value = "子级集合")
    private List<?> children;

    @ApiModelProperty(value = "默认展开")
    private boolean spread = true;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    @Override
    public String toString() {
        return "PermissionRespNodeVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", code='" + code + '\'' +
                ", perms='" + perms + '\'' +
                ", pid='" + pid + '\'' +
                ", orderNum=" + orderNum +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", children=" + children +
                ", spread=" + spread +
                '}';
    }
}
