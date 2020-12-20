package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author kehong
 */
public class HomeRespVO {
    @ApiModelProperty("用户信息")
    private UserInfoRespVO userInfo;
    @ApiModelProperty("目录菜单")
    private List<PermissionRespNodeVO> menus;

    public UserInfoRespVO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoRespVO userInfo) {
        this.userInfo = userInfo;
    }

    public List<PermissionRespNodeVO> getMenus() {
        return menus;
    }

    public void setMenus(List<PermissionRespNodeVO> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "HomeRespVO{" +
                "userInfo=" + userInfo +
                ", menus=" + menus +
                '}';
    }
}
