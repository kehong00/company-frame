package cn.codewoo.mapper;

import cn.codewoo.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> selectAll();

    /**
     * 逻辑删除，设置delete = 0
     * @param id
     * @return
     */
    int updateDeleteById(String id);

    /**
     * 根据角色id查询拥有的权限
     * @param roleId
     * @return
     */
    List<SysPermission> selectRolePermissionByRoleId(String roleId);
}