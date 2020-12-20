package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author KehongWu
 */
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

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurPageSize() {
        return curPageSize;
    }

    public void setCurPageSize(Integer curPageSize) {
        this.curPageSize = curPageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageRespVO{" +
                "totalRows=" + totalRows +
                ", totalPages=" + totalPages +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", curPageSize=" + curPageSize +
                ", list=" + list +
                '}';
    }
}
