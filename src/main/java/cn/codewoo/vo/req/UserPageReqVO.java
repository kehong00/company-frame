package cn.codewoo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPageReqVO {
    @ApiModelProperty("当前页数")
    private Integer pageNum = 1;
    @ApiModelProperty("当前页的总数")
    private Integer pageSize = 10;
}
