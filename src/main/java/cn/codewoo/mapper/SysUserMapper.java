package cn.codewoo.mapper;

import cn.codewoo.entity.SysUser;
import cn.codewoo.vo.req.UserPageReqVO;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByUsername(String username);

    /**
     * 查询全部角色记录
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 查询用户详情，包含用户信息，角色信息，权限信息
     * @param userId
     * @return
     */
    SysUser selectDetailedById(String userId);

    /**
     * 逻辑删除用户
     * @param id
     * @return
     */
    int updateDeleteById(String id);


    /**
     * 条件查询用户
     * @param vo
     * @return
     */
    List<SysUser> userQuery(UserPageReqVO vo);
}
