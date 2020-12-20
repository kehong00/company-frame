package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author KehongWu
 */
public class RolePageReqVO {
    @ApiModelProperty("第几页")
    private Integer pageNum = 1;
    @ApiModelProperty("每页显示的条数")
    private Integer pageSize;

    @ApiModelProperty("角色id")
    private String roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "RolePageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
