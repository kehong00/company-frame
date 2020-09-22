package cn.codewoo.service;

import cn.codewoo.entity.SysRole;
import cn.codewoo.vo.req.RolePageReqVO;
import cn.codewoo.vo.resp.PageRespVO;

/**
 * @author KehongWu
 */
public interface IRoleService {
    PageRespVO<SysRole> pageInfo(RolePageReqVO vo);
}
