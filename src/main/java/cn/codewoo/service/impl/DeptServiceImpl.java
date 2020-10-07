package cn.codewoo.service.impl;

import cn.codewoo.entity.SysDept;
import cn.codewoo.mapper.SysDeptMapper;
import cn.codewoo.service.IDeptService;
import cn.codewoo.vo.req.DeptAddReqVO;
import cn.codewoo.vo.resp.DeptRespNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kehong
 * 部门服务层实现类
 */
@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired(required = false)
    private SysDeptMapper deptMapper;
    @Override
    public List<SysDept> selectAll() {
        List<SysDept> sysDepts = deptMapper.selectAll();
        for (SysDept sysDept : sysDepts) {
            SysDept dept = deptMapper.selectByPrimaryKey(sysDept.getPid());
            if (dept != null){
                sysDept.setPidName(dept.getName());
            }
        }

        return sysDepts;
    }

    @Override
    public List<DeptRespNodeVO> deptTreeList() {
        List<SysDept> all = deptMapper.selectAll();
        List<DeptRespNodeVO> list = new ArrayList<>();
        DeptRespNodeVO vo = new DeptRespNodeVO();
        vo.setId("0");
        vo.setTitle("默认顶级部门");
        vo.setChildren(getTree(all));
        list.add(vo);
        return list;
    }

    @Override
    public SysDept insert(DeptAddReqVO vo) {

        return null;
    }

    private List<DeptRespNodeVO> getTree(List<SysDept> all){
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept dept : all) {
            if ("0".equals(dept.getPid())) {
                DeptRespNodeVO vo = new DeptRespNodeVO();
                vo.setId(dept.getId());
                vo.setTitle(dept.getName());
                vo.setChildren(getChildren(dept.getId(),all));
                list.add(vo);
            }
        }
        return list;
    }

    private List<DeptRespNodeVO> getChildren(String id,List<SysDept> all){
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept dept : all) {
            if (id.equals(dept.getPid())){
                DeptRespNodeVO vo = new DeptRespNodeVO();
                vo.setId(dept.getId());
                vo.setTitle(dept.getName());
                vo.setChildren(getChildren(dept.getId(),all));
                list.add(vo);
            }
        }
        return list;
    }
}
