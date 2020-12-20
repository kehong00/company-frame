package cn.codewoo.vo.req;

/**
 * @ClassName DeptEditReqVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/14 3:56 下午
 * @Version 1.0
 **/
public class DeptEditReqVO {
    private String id;
    private String name;
    private String pid;
    private String managerName;
    private String phone;
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
        return "DeptEditReqVO{" +
                "name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
