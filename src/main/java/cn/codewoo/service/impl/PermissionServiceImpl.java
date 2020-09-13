package cn.codewoo.service.impl;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.mapper.SysPermissionMapper;
import cn.codewoo.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kehong
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Override
    public List<SysPermission> getAllPermission() {
        List<SysPermission> result = sysPermissionMapper.selectAll();
        //获取每个菜单的父菜单
        if (!result.isEmpty()){
            for (SysPermission sysPermission : result) {
                if (sysPermission.getPid() != null){
                    //通过父id查询父菜单名称
                    SysPermission parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
                    //查询到父菜单信息，将父菜单名称放到行中
                    if (parent != null){
                        sysPermission.setPidName(parent.getName());
                    }
                }
            }
        }
        return result;
    }
}
