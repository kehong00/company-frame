package cn.codewoo.service.impl;

import cn.codewoo.entity.SysRole;
import cn.codewoo.mapper.SysRoleMapper;
import cn.codewoo.service.IRoleService;
import cn.codewoo.utils.PageUtil;
import cn.codewoo.vo.req.RolePageReqVO;
import cn.codewoo.vo.resp.PageRespVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KehongWu
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Override
    public PageRespVO<SysRole> pageInfo(RolePageReqVO vo) {
        PageHelper.offsetPage(vo.getPageNum(),vo.getPageSize());
        List<SysRole> sysRoles = sysRoleMapper.selectAll(vo);
        return PageUtil.getPageRespVO(sysRoles);
    }
}
