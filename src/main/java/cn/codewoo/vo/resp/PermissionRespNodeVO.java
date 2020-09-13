package cn.codewoo.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author kehong
 */
@Data
public class PermissionRespNodeVO {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "菜单权限名称")
    private String title;
    @ApiModelProperty(value = "接口地址")
    private String url;
    @ApiModelProperty(value = "子级集合")
    private List<?> children;

    @ApiModelProperty(value = "默认展开")
    private boolean spread = true;
}
