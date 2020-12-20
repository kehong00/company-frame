package cn.codewoo.service;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.vo.req.PermissionAddReqVO;
import cn.codewoo.vo.req.SysPermissionEditReqVO;
import cn.codewoo.vo.resp.PermissionRespNodeVO;

import java.util.List;

/**
 * @author kehong
 */
public interface IPermissionService {
    /**
     * 获取所有菜单权限记录
     * @return
     */
    List<SysPermission> getAllPermission();

    /**
     * 遍历菜单权限树，排除按钮
     * @return
     */
    List<PermissionRespNodeVO> selectAllMenuByTreeExBtn();

    /**
     * 获得所有菜单权限树，不排除按钮
     * @return
     */
    List<PermissionRespNodeVO> selectAllPermissionTree();

    /**
     * 添加菜单权限
     * @param vo
     * @return
     */
    SysPermission addPermission(PermissionAddReqVO vo);

    /**
     * 根据用户id获取菜单权限树
     * @param userId
     * @return
     */
    List<PermissionRespNodeVO> getPermissionTree(String userId);


    /**
     * 获取菜单树形数据
     * @param permissions
     * @return
     */
    List<PermissionRespNodeVO> getMenuTree(List<SysPermission> permissions);

    /**
     * 根据id获取权限信息
     * @param id
     * @return
     */
    SysPermission selectById(String id);

    /**
     * 更新权限记录
     * @param vo
     * @return
     */
    int updatePermissionById(SysPermissionEditReqVO vo);

    /**
     * 删除权限记录
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 获取角色拥有的权限，封装树
     * @param roleId
     * @return
     */
    List<PermissionRespNodeVO> selectRolePermissionTree(String roleId);

    /**
     * 获取角色拥有的权限，不封装树
     * @param roleId
     * @return
     */
    List<SysPermission> selectRolePermissionList(String roleId);
}
