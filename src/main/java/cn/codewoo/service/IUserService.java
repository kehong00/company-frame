package cn.codewoo.service;

import cn.codewoo.entity.SysUser;
import cn.codewoo.vo.req.LoginReqVO;
import cn.codewoo.vo.req.UserAddReqVO;
import cn.codewoo.vo.req.UserPageReqVO;
import cn.codewoo.vo.resp.LoginRespVO;
import cn.codewoo.vo.resp.PageRespVO;
import cn.codewoo.vo.resp.UserPersonalRespVO;

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
    PageRespVO<SysUser> userPageInfo(UserPageReqVO vo);

    /**
     * 获取角色详情，包含用户信息、角色信息、权限信息
     * @param userId
     * @return
     */
    SysUser selectDetailedById(String userId);

    /**
     * 注册用户
     * @param vo
     * @return
     */
    int register(UserAddReqVO vo);

    /**
     * 逻辑删除用户
     * @param id
     * @return
     */
    int deleteUserById(String id);

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    String refreshToken(String refreshToken);


    /**
     * 用户退出登录服务接口
     * @param accessToken
     * @param refreshToken
     * @return
     */
    int loginOut(String accessToken, String refreshToken);

    /**
     * 根据获取用户信息
     * @param userId
     * @return
     */
    UserPersonalRespVO getUserInfo(String userId);
}
