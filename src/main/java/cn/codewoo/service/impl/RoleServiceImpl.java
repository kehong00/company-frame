package cn.codewoo.service.impl;

import cn.codewoo.entity.SysRole;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.mapper.SysRoleMapper;
import cn.codewoo.service.IRolePermissionService;
import cn.codewoo.service.IRoleService;
import cn.codewoo.utils.PageUtil;
import cn.codewoo.vo.req.RoleAddReqVo;
import cn.codewoo.vo.req.RoleEditReqVO;
import cn.codewoo.vo.req.RolePageReqVO;
import cn.codewoo.vo.req.RolePermissionOperationReqVO;
import cn.codewoo.vo.resp.PageRespVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author KehongWu
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired(required = false)
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private IRolePermissionService rolePermissionService;



    @Override
    public PageRespVO<SysRole> pageInfo(RolePageReqVO vo) {
//        PageHelper.offsetPage(vo.getPageNum(),vo.getPageSize());
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysRole> sysRoles = sysRoleMapper.selectPageInfo(vo);
        return PageUtil.getPageRespVO(sysRoles);
    }

    /**
     * 添加角色记录，添加同时需要在角色与菜单权限关联表中添加相关记录
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysRole addRole(RoleAddReqVo vo) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(vo,sysRole);
        sysRole.setId(UUID.randomUUID().toString());
        sysRole.setCreateTime(new Date());
        int result = sysRoleMapper.insertSelective(sysRole);
        if (result != 1){
            throw new BusinessException(BaseResponseCodeImpl.OPERATION_ERROR);
        }
        if (vo.getPermissionIds() != null && !vo.getPermissionIds().isEmpty()){
            RolePermissionOperationReqVO rolePermissionOperationReqVO = new RolePermissionOperationReqVO();
            rolePermissionOperationReqVO.setRoleId(sysRole.getId());
            rolePermissionOperationReqVO.setPermissionIds(vo.getPermissionIds());
            rolePermissionService.addRolePermission(rolePermissionOperationReqVO);
        }
        return sysRole;
    }

    @Override
    public List<SysRole> selectUserRoleByUserId(String userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectRoleByUserId(userId);
        return sysRoles;
    }

    @Override
    public List<SysRole> selectAll() {
        List<SysRole> sysRoles = sysRoleMapper.selectPageInfo();
        return sysRoles;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,BusinessException.class})
    public int editRole(RoleEditReqVO vo) {
        //修改角色基本信息
        SysRole sysRole = new SysRole();
        sysRole.setName(vo.getName());
        sysRole.setDescription(vo.getDescription());
        sysRole.setStatus(vo.getStatus());
        sysRole.setUpdateTime(new Date());
        sysRole.setId(vo.getId());
        int roleUpRow = sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        if (roleUpRow != 1){
            throw new BusinessException(BaseResponseCodeImpl.SYS_ERROR);
        }
        //删除原有的权限关联
        int delRow = rolePermissionService.deleteByRoleId(vo.getId());
        if (delRow < 0){
            throw new BusinessException(BaseResponseCodeImpl.SYS_ERROR);
        }
        //重新插入新的角色权限关联记录
        RolePermissionOperationReqVO operationReqVO = new RolePermissionOperationReqVO();
        operationReqVO.setRoleId(vo.getId());
        operationReqVO.setPermissionIds(vo.getPermissionIds());
        rolePermissionService.addRolePermission(operationReqVO);
        return 1;
    }

    @Override
    public SysRole selectRoleById(String id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }
}
