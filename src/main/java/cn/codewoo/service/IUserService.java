package cn.codewoo.service;

import cn.codewoo.entity.SysUser;
import cn.codewoo.vo.req.LoginReqVO;
import cn.codewoo.vo.req.UserPageReqVO;
import cn.codewoo.vo.resp.LoginRespVO;
import cn.codewoo.vo.resp.PageRespVO;

public interface IUserService {
    /**
     * 用户登录
     * @param vo
     * @return
     */
    LoginRespVO login(LoginReqVO vo);

    /**
     * 分页查询角色信息
     * @param vo
     * @return
     */
    PageRespVO<SysUser> pageInfo(UserPageReqVO vo);
}
