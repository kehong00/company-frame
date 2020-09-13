package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author kehong
 */
@Data
@Builder
public class HomeRespVO {
    @ApiModelProperty("用户信息")
    private UserInfoRespVO userInfo;
    @ApiModelProperty("目录菜单")
    private List<PermissionRespNodeVO> menus;
}
