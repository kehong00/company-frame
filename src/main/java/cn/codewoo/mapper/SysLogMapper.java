package cn.codewoo.mapper;

import cn.codewoo.entity.SysLog;
import cn.codewoo.vo.req.SysLogPageReqVO;

import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    /**
     * 分页查询日志记录
     * @param vo
     * @return
     */
    List<SysLog> selectPage(SysLogPageReqVO vo);

    /**
     * 批量删除日志记录
     * @param ids
     * @return
     */
    int batchDeleteById(List<String> ids);
}