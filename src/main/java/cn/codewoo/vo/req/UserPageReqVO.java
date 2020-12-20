package cn.codewoo.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class UserPageReqVO {
    @ApiModelProperty("当前页数")
    private Integer pageNum = 1;
    @ApiModelProperty("当前页的总数")
    private Integer pageSize = 10;

    private String username;
    private String userId;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endTime;
    private String nickName;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "UserPageReqVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
