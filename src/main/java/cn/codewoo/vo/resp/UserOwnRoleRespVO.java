package cn.codewoo.vo.resp;

import cn.codewoo.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName UserOwnRoleRespVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/17 10:34 上午
 * @Version 1.0
 **/
public class UserOwnRoleRespVO {
    @ApiModelProperty(value = "用户拥有的角色")
    private List<SysRole> userRoleList;
    @ApiModelProperty(value = "全部角色列表")
    private List<SysRole> roleList;

    public List<SysRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<SysRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserOwnRoleRespVO{" +
                "userRoleList=" + userRoleList +
                ", roleList=" + roleList +
                '}';
    }
}
