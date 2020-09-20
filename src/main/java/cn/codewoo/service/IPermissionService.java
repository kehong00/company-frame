package cn.codewoo.service;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.vo.resp.PermissionRespNodeVO;

import java.util.List;

/**
 * @author kehong
 */
public interface IPermissionService {
    List<SysPermission> getAllPermission();

    List<PermissionRespNodeVO> selectAllMenuByTree();
}
