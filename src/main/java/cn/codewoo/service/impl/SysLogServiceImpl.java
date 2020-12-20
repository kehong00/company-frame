package cn.codewoo.service.impl;

import cn.codewoo.entity.SysLog;
import cn.codewoo.mapper.SysLogMapper;
import cn.codewoo.service.ISysLogService;
import cn.codewoo.utils.PageUtil;
import cn.codewoo.vo.req.SysLogPageReqVO;
import cn.codewoo.vo.resp.PageRespVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysLogServiceImpl
 * @Description TODO
 * @Author kehong
 * @Date 2020/12/18 3:14 下午
 * @Version 1.0
 **/
@Service
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public PageRespVO<SysLog> selectLogPage(SysLogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageNum());
        List<SysLog> sysLogs = sysLogMapper.selectPage(vo);
        PageRespVO<SysLog> pageRespVO = PageUtil.getPageRespVO(sysLogs);
        return pageRespVO;
    }
}
