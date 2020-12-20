package cn.codewoo.service;


import cn.codewoo.entity.SysLog;
import cn.codewoo.vo.req.SysLogPageReqVO;
import cn.codewoo.vo.resp.PageRespVO;

import java.util.List;

/**
 * @author kehong
 * 系统日志相关接口
 */
public interface ISysLogService {
    /**
     * 日志分页查询
     * @param vo
     * @return
     */
    PageRespVO<SysLog> selectLogPage(SysLogPageReqVO vo);
}
