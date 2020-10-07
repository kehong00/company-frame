package cn.codewoo.service;

import cn.codewoo.entity.SysDept;
import cn.codewoo.vo.req.DeptAddReqVO;
import cn.codewoo.vo.resp.DeptRespNodeVO;

import java.util.List;

/**
 * @author kehong
 * 部门服务层
 */
public interface IDeptService {
    /**
     * 查询所有部门记录
     * @return
     */
    List<SysDept> selectAll();

    /**
     * 部门树形列表
     * @return
     */
    List<DeptRespNodeVO> deptTreeList();

    /**
     * 插入部门
     * @param vo
     * @return
     */
    SysDept insert(DeptAddReqVO vo);
}
