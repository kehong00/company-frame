package cn.codewoo.service;

import cn.codewoo.entity.SysPermission;

import java.util.List;

/**
 * @author kehong
 */
public interface IPermissionService {
    List<SysPermission> getAllPermission();
}
