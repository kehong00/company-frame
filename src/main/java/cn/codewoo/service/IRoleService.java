package cn.codewoo.service;

import cn.codewoo.entity.SysRole;
import cn.codewoo.vo.req.RolePageReqVO;
import cn.codewoo.vo.resp.PageRespVO;

/**
 * @author KehongWu
 */
public interface IRoleService {
    /**
     * 分页查询角色信息
     * @param vo
     * @return
     */
    PageRespVO<SysRole> pageInfo(RolePageReqVO vo);
}
