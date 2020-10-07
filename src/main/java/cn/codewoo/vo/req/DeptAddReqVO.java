package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author kehong
 * 添加部门数据VO
 */
@Data
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
}
