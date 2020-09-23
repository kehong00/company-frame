package cn.codewoo.service;

import cn.codewoo.vo.resp.HomeRespVO;
import cn.codewoo.vo.resp.UserInfoRespVO;

/**
 * @author kehong
 */
public interface IHomeService {
    /**
     * 获取主页显示的用户信息，菜单
     * @param userId
     * @return
     */
    HomeRespVO getHomeInfo(String userId);
}
