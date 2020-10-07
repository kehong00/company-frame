package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author KehongWu
 */
@Data
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
}
