package cn.codewoo.service;

import cn.codewoo.entity.SysUser;
import cn.codewoo.vo.req.LoginReqVO;
import cn.codewoo.vo.req.UserPageReqVO;
import cn.codewoo.vo.resp.LoginRespVO;
import cn.codewoo.vo.resp.PageRespVO;

public interface IUserService {
    LoginRespVO login(LoginReqVO vo);

    PageRespVO<SysUser> pageInfo(UserPageReqVO vo);
}
