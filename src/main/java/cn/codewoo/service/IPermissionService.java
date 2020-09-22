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
     * 遍历菜单权限树
     * @return
     */
    List<PermissionRespNodeVO> selectAllMenuByTree();

    /**
     * 添加菜单权限
     * @param vo
     * @return
     */
    SysPermission addPermission(PermissionAddReqVO vo);

    /**
     * 获取用户菜单权限列表
     */

    List<PermissionRespNodeVO> getPermissionTree(String userId);
}
