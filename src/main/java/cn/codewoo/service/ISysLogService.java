package cn.codewoo.service;


import cn.codewoo.entity.SysLog;
import cn.codewoo.vo.req.LogBatchDelReqVO;
import cn.codewoo.vo.req.SysLogPageReqVO;
import cn.codewoo.vo.resp.PageRespVO;

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

    /**
     * 删除日志记录
     * @param id
     * @return
     */
    int deleteLog(String id);

    /**
     * 批量删除日志记录
     * @param vo
     * @return
     */
    int batchDelete(LogBatchDelReqVO vo);
}
