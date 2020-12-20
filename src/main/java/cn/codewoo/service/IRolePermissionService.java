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

    /**
     * 根据角色id删除关联记录
     * @param roleId
     * @return
     */
    int deleteByRoleId(String roleId);
}
