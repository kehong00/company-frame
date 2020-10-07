package cn.codewoo.vo.resp;

import lombok.Data;

import java.util.List;

/**
 * @author kehong
 * 部门树形节点响应VO
 */
@Data
public class DeptRespNodeVO {
    private String id;
    private String title;
    private boolean spread = true;
    private List<?> children;
}
