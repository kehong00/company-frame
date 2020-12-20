package cn.codewoo.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName SysLogPageReqVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/18 3:11 下午
 * @Version 1.0
 **/
public class SysLogPageReqVO {
    @ApiModelProperty("当前页数")
    private Integer pageNum = 1;
    @ApiModelProperty("当前页的总数")
    private Integer pageSize = 10;

    private String userId;
    private String username;
    private String action;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endTime;

    public SysLogPageReqVO() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "SysLogPageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", action='" + action + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
