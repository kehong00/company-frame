package cn.codewoo.service;

import cn.codewoo.vo.resp.HomeRespVO;
import cn.codewoo.vo.resp.UserInfoRespVO;

/**
 * @author kehong
 */
public interface IHomeService {
    HomeRespVO getHomeInfo(String userId);
}
