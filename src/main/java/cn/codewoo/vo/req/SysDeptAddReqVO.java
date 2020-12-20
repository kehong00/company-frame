package cn.codewoo.vo.req;

/**
 * @ClassName SysDeptAddReqVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/14 10:41 上午
 * @Version 1.0
 **/
public class SysDeptAddReqVO {
    private String name;
    private String pid;
    private Integer status;
    private String phone;
    private String managerName;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return "SysDeptAddReqVO{" +
                "name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
