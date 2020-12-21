package cn.codewoo.vo.req;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName UserOwnRoleEditReqVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/17 11:23 上午
 * @Version 1.0
 **/
public class UserOwnRoleEditReqVO {
    @NotNull(message = "用户id不能为空")
    private String userId;
    private List<String> roleIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(final List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "UserOwnRoleEditReqVO{" +
                "userId='" + userId + '\'' +
                ", roleIds=" + roleIds +
                '}';
    }
}
