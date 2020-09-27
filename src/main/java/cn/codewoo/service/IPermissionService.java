package cn.codewoo.service;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.vo.req.PermissionAddReqVO;
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
}
