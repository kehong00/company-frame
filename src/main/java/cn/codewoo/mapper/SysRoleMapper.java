package cn.codewoo.mapper;

import cn.codewoo.entity.SysRole;
import cn.codewoo.vo.req.RolePageReqVO;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectPageInfo(RolePageReqVO vo);

    /**
     * 查询用户拥有的角色
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(String userId);

    List<SysRole> selectPageInfo();
}