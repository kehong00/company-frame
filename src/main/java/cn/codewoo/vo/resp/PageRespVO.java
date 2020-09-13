package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@ApiModel("分页封装类")
public class PageRespVO<T> {
    @ApiModelProperty("总记录条数")
    private Long totalRows;

    @ApiModelProperty("总页数")
    private Integer totalPages;

    @ApiModelProperty("当前页数")
    private Integer pageNum;

    @ApiModelProperty("每页记录条数")
    private Integer pageSize;

    @ApiModelProperty("当前页记录条数")
    private Integer curPageSize;

    @ApiModelProperty("列表数据")
    private List<T> list;
}
