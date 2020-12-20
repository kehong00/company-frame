package cn.codewoo.vo.req;

/**
 * @ClassName UserAddReqVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/15 9:07 上午
 * @Version 1.0
 **/
public class UserAddReqVO {
    private String username;
    private String password;
    private String phone;
    private String deptId;
    private String realName;
    private String nickName;
    private String email;
    private Integer sex;
    private Integer status;
    private Integer createWhere;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateWhere() {
        return createWhere;
    }

    public void setCreateWhere(Integer createWhere) {
        this.createWhere = createWhere;
    }

    @Override
    public String toString() {
        return "UserAddReqVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", deptId='" + deptId + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", createWhere=" + createWhere +
                '}';
    }
}
