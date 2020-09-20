package cn.codewoo.service.impl;

import cn.codewoo.entity.SysPermission;
import cn.codewoo.mapper.SysPermissionMapper;
import cn.codewoo.service.IPermissionService;
import cn.codewoo.vo.resp.PermissionRespNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<PermissionRespNodeVO> selectAllMenuByTree() {
        return getTree(sysPermissionMapper.selectAll());
    }

    private List<PermissionRespNodeVO>  getTree(List<SysPermission> all){
        List<PermissionRespNodeVO> list = new ArrayList<>();
        if (all.isEmpty()){
            return list;
        }
        for (SysPermission permission : all) {
            //最顶级菜单
            if ("0".equals(permission.getPid())){
                PermissionRespNodeVO vo = PermissionRespNodeVO.builder()
                        .id(permission.getId())
                        .url(permission.getUrl())
                        .title(permission.getName())
                        .children(getChild(permission.getId(),all))
                        .build();
                list.add(vo);
            }
        }
        return list;
    }

    private List<PermissionRespNodeVO> getChild(String pId,List<SysPermission> all){
        List<PermissionRespNodeVO> list = new ArrayList<>();
        for (SysPermission permission : all) {
            if (pId.equals(permission.getPid())){
                PermissionRespNodeVO vo = PermissionRespNodeVO.builder()
                        .id(permission.getId())
                        .url(permission.getUrl())
                        .title(permission.getName())
                        .children(getChild(permission.getId(),all))
                        .build();
                list.add(vo);
            }
        }

        return list;
    }
}
