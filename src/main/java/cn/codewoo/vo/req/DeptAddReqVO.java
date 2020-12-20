package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author kehong
 * 添加部门数据VO
 */
public class DeptAddReqVO {
    @ApiModelProperty("部门名称")
    @NotNull(message = "部门名称不能为空")
    private String name;
    @ApiModelProperty("父级id")
    @NotNull(message = "父级id不能为空")
    private String pid;

    @ApiModelProperty("部门经理名称")
    private String managerName;

    @ApiModelProperty("部门经理电话")
    private String phone;

    @ApiModelProperty("状态（1：正常，2：弃用）")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeptAddReqVO{" +
                "name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
