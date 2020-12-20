package cn.codewoo.controller;

import cn.codewoo.entity.SysLog;
import cn.codewoo.service.ISysLogService;
import cn.codewoo.utils.DataResult;
import cn.codewoo.vo.req.SysLogPageReqVO;
import cn.codewoo.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SysLogController
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/18 3:09 下午
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
@Api(tags = "系统日志相关接口")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;
    @PostMapping("/auth/api/log/page")
    @ApiOperation("分页获取日志记录")
    public DataResult<PageRespVO<SysLog>> getLogPage(@RequestBody SysLogPageReqVO vo){
        return DataResult.success(sysLogService.selectLogPage(vo));
    }
}
