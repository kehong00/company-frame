package cn.codewoo.service.impl;

import cn.codewoo.entity.SysRolePermission;
import cn.codewoo.mapper.SysRolePermissionMapper;
import cn.codewoo.service.IRolePermissionService;
import cn.codewoo.vo.req.RolePermissionOperationReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author KehongWu
 */
@Service
public class RolePermissionServiceImpl implements IRolePermissionService {
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Autowired
    public void setSysRolePermissionMapper(SysRolePermissionMapper sysRolePermissionMapper) {
        this.sysRolePermissionMapper = sysRolePermissionMapper;
    }
    @Override
    public void addRolePermission(RolePermissionOperationReqVO vo) {
        if (vo == null || vo.getPermissionIds().isEmpty()){
            return ;
        }
        List<SysRolePermission> list = new ArrayList<>();

        for (String permissionId : vo.getPermissionIds()) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setId(UUID.randomUUID().toString());
            sysRolePermission.setRoleId(vo.getRoleId());
            sysRolePermission.setCreateTime(new Date());
            sysRolePermission.setPermissionId(permissionId);
            list.add(sysRolePermission);
        }
        sysRolePermissionMapper.batchInsert(list);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return sysRolePermissionMapper.deleteByRoleId(roleId);
    }
}
