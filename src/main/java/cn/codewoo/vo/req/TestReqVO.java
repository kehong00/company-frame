package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
