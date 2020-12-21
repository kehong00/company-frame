package cn.codewoo.vo.resp;

/**
 * @ClassName UserPersonalRespVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/21 8:50 上午
 * @Version 1.0
 **/
public class UserPersonalRespVO {
    private String username;
    private String phone;
    private String deptName;
    private String realName;
    private String nickName;
    private String email;
    private Integer sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    @Override
    public String toString() {
        return "UserPersonalRespVO{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", deptName='" + deptName + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                '}';
    }
}
