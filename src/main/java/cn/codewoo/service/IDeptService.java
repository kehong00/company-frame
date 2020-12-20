package cn.codewoo.service;

import cn.codewoo.entity.SysDept;
import cn.codewoo.vo.req.DeptAddReqVO;
import cn.codewoo.vo.req.DeptEditReqVO;
import cn.codewoo.vo.req.SysDeptAddReqVO;
import cn.codewoo.vo.resp.DeptRespNodeVO;

import java.util.List;

/**
 * @author kehong
 * 部门服务层
 */
public interface IDeptService {
    /**
     * 获取所有部门记录
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

    /**
     * 添加部门
     * @param vo
     * @return
     */
    int addDept(SysDeptAddReqVO vo);

    /**
     * 获取部门信息
     * @param id
     * @return
     */
    SysDept selectById(String id);

    /**
     * 编辑部门信息
     * @param vo
     * @return
     */
    int editDept(DeptEditReqVO vo);
}
