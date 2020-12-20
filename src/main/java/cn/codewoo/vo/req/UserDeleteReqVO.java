package cn.codewoo.vo.req;

import java.util.List;

/**
 * @ClassName UserDeleteReqVO
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/15 3:15 下午
 * @Version 1.0
 **/
public class UserDeleteReqVO {
    private List<String> userIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public String toString() {
        return "UserDeleteReqVO{" +
                "userIds=" + userIds +
                '}';
    }
}
