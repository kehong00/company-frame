package cn.codewoo.service;

import cn.codewoo.entity.SysRole;
import cn.codewoo.vo.req.RoleAddReqVo;
import cn.codewoo.vo.req.RoleEditReqVO;
import cn.codewoo.vo.req.RolePageReqVO;
import cn.codewoo.vo.resp.PageRespVO;

import java.util.List;

/**
 * @author KehongWu
 */
public interface IRoleService {
    /**
     * 分页查询角色信息
     * @param vo
     * @return
     */
    PageRespVO<SysRole> pageInfo(RolePageReqVO vo);

    /**
     * 新增角色记录
     * @param vo
     * @return
     */
    SysRole addRole(RoleAddReqVo vo);

    /**
     * 查询用户拥有的角色
     * @param userId
     * @return
     */
    List<SysRole> selectUserRoleByUserId(String userId);

    /**
     * 获取所有角色列表
     * @return
     */
    List<SysRole> selectAll();

    /**
     * 编辑角色信息
     * @param vo
     * @return
     */
    int editRole(RoleEditReqVO vo);

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    SysRole selectRoleById(String id);

    /**
     * 删除角色记录
     * @param ids
     * @return
     */
    int deleteRoleById(String[] ids);
}
