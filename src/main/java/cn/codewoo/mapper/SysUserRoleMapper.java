package cn.codewoo.mapper;

import cn.codewoo.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    /**
     * 根据用户id删除关联记录
     * @param userId
     * @return
     */
    int deleteByUserId(String userId);

    /**
     * 批量插入用户角色关联记录
     * @param list
     * @return
     */
    int batchInsert(List<SysUserRole> list);
}