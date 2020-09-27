package cn.codewoo.mapper;

import cn.codewoo.entity.SysRolePermission;
import cn.codewoo.vo.req.RolePermissionOperationReqVO;

import java.util.List;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

    void batchInsert(List<SysRolePermission> vo);
}