package cn.codewoo.service.impl;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.mapper.SysPermissionMapper;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.vo.req.PermissionAddReqVO;
import cn.codewoo.vo.resp.PermissionRespNodeVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author kehong
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    private SysPermissionMapper sysPermissionMapper;

    @Autowired(required = false)
    public void setSysPermissionMapper(SysPermissionMapper sysPermissionMapper) {
        this.sysPermissionMapper = sysPermissionMapper;
    }

    @Override
    public List<SysPermission> getAllPermission() {
        List<SysPermission> result = sysPermissionMapper.selectAll();
        //获取每个菜单的父菜单
        if (!result.isEmpty()) {
            for (SysPermission sysPermission : result) {
                if (sysPermission.getPid() != null) {
                    //通过父id查询父菜单名称
                    SysPermission parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
                    //查询到父菜单信息，将父菜单名称放到行中
                    if (parent != null) {
                        sysPermission.setPidName(parent.getName());
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<PermissionRespNodeVO> selectAllMenuByTree() {
        List<SysPermission> list = sysPermissionMapper.selectAll();
        List<PermissionRespNodeVO> result = new ArrayList<>();
//新增顶级目录是为了方便添加一级目录
        PermissionRespNodeVO respNode = new PermissionRespNodeVO();
        respNode.setId("0");
        respNode.setTitle("默认顶级菜单");
        respNode.setSpread(true);
        respNode.setChildren(getTree(list));
        result.add(respNode);
        return result;
    }

    @Override
    public SysPermission addPermission(PermissionAddReqVO vo) {
        if (vo == null) {
            throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
        }

        //校验添加的菜单权限pid信息是否合法
        verity(vo);

        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(vo, sysPermission);
        sysPermission.setCreateTime(new Date());
        sysPermission.setId(UUID.randomUUID().toString());
        int result = sysPermissionMapper.insertSelective(sysPermission);
        if (result != 1) {
            throw new BusinessException(BaseResponseCodeImpl.OPERATION_ERROR);
        }
        return sysPermission;
    }

    @Override
    public List<PermissionRespNodeVO> getPermissionTree(String userId) {
        return getTree(sysPermissionMapper.selectAll());
    }

    private List<PermissionRespNodeVO> getTree(List<SysPermission> all) {
        List<PermissionRespNodeVO> list = new ArrayList<>();
        if (all.isEmpty()) {
            return list;
        }
        for (SysPermission permission : all) {
            //最顶级菜单
            if ("0".equals(permission.getPid())) {
                PermissionRespNodeVO vo = PermissionRespNodeVO.builder()
                        .id(permission.getId())
                        .url(permission.getUrl())
                        .title(permission.getName())
                        .children(getChild(permission.getId(), all))
                        .build();
                list.add(vo);
            }
        }
        return list;
    }

    private List<PermissionRespNodeVO> getChild(String pId, List<SysPermission> all) {
        List<PermissionRespNodeVO> list = new ArrayList<>();
        for (SysPermission permission : all) {
            if (pId.equals(permission.getPid())) {
                PermissionRespNodeVO vo = PermissionRespNodeVO.builder()
                        .id(permission.getId())
                        .url(permission.getUrl())
                        .title(permission.getName())
                        .children(getChild(permission.getId(), all))
                        .build();
                list.add(vo);
            }
        }

        return list;
    }

    /**
     * 验证添加菜单权限父级是否合法
     * 类型为目录时，父级类型必须为目录
     * 类型为菜单时，父级类型必须是目录
     * 类型为按钮时，父级类型必须为菜单
     *
     * @param vo
     */
    private void verity(PermissionAddReqVO vo) {
        SysPermission parent = sysPermissionMapper.selectByPrimaryKey(vo.getPid());
        switch (vo.getType()) {
            case 1:
                if (parent != null) {
                    if (parent.getType() != 1) {
                        throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }
                    if (!"0".equals(vo.getPid())) {
                        throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }
                } else if (!"0".equals(vo.getPid())) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                }
                break;
            case 2:
                if (parent.getType() != 1) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_MENU_ERROR);
                }
                if (Strings.isEmpty(vo.getUrl())) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                /*if (Strings.isEmpty(vo.getPerms())) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }*/
                if (Strings.isEmpty(vo.getMethod())) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }
                break;
            case 3:
                if (parent.getType() != 2) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_BTN_ERROR);
                }
                if (Strings.isEmpty(vo.getUrl())) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                /*if (Strings.isEmpty(vo.getPerms())) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }*/
                if (Strings.isEmpty(vo.getMethod())) {
                    throw new BusinessException(BaseResponseCodeImpl.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }
                break;
        }
    }
}
