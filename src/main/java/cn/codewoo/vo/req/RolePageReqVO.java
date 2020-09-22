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
}
