package cn.codewoo.controller;

import cn.codewoo.constant.Constant;
import cn.codewoo.service.IHomeService;
import cn.codewoo.service.impl.HomeServiceImpl;
import cn.codewoo.utils.DataResult;
import cn.codewoo.utils.JwtTokenUtil;
import cn.codewoo.vo.resp.HomeRespVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author kehong
 */
@RestController
@RequestMapping("/api")
@Api(tags = "首页数据")
@Slf4j
public class HomeController {
    @Autowired
    private IHomeService homeService;
    @GetMapping("/home")
    /**
     * 返回用户首页展示的数据，用户id、权限菜单列表
     */
    public DataResult<HomeRespVO> getHomeInfo(HttpServletRequest request){
        String token = request.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(token);
        DataResult result = DataResult.success(homeService.getHomeInfo(userId));
        return result;
    }
}
