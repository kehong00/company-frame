package cn.codewoo.controller;

import cn.codewoo.aop.annotation.MyLog;
import cn.codewoo.constant.Constant;
import cn.codewoo.service.IHomeService;
import cn.codewoo.service.impl.HomeServiceImpl;
import cn.codewoo.utils.CommonUtils;
import cn.codewoo.utils.DataResult;
import cn.codewoo.utils.JwtTokenUtil;
import cn.codewoo.vo.resp.HomeRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

public class HomeController {
    @Autowired
    private IHomeService homeService;
    @GetMapping("/auth/home")
    /**
     * 返回用户首页展示的数据，用户id、权限菜单列表
     */
    @ApiOperation("获取首页展示数据")
    @MyLog(title = "首页数据",action = "获取首页数据")
    public DataResult<HomeRespVO> getHomeInfo(HttpServletRequest request){
        String token = CommonUtils.getToken(request);
        String userId = JwtTokenUtil.getUserId(token);
        DataResult result = DataResult.success(homeService.getHomeInfo(userId));
        return result;
    }
}
