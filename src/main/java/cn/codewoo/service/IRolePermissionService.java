package cn.codewoo.service;

import cn.codewoo.vo.req.RolePermissionOperationReqVO;

/**
 *
 */
public interface IRolePermissionService {
    /**
     * 添加角色与菜单权限关联信息
     * @param VO
     */
    void addRolePermission(RolePermissionOperationReqVO VO);
}
