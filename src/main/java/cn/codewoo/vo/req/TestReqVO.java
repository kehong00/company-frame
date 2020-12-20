package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@ApiModel("validator测试")
public class TestReqVO {
    @NotEmpty(message = "list数据不能为空")
    @ApiModelProperty("list集合数据")
    private List<String> list;
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;
    @NotNull(message = "年龄不能为空")
    @ApiModelProperty("年龄")
    private int age;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestReqVO{" +
                "list=" + list +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
