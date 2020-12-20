package cn.codewoo.service.impl;

import cn.codewoo.constant.Constant;
import cn.codewoo.entity.SysDept;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.mapper.SysDeptMapper;
import cn.codewoo.service.IDeptService;
import cn.codewoo.service.RedisService;
import cn.codewoo.utils.CommonUtils;
import cn.codewoo.vo.req.DeptAddReqVO;
import cn.codewoo.vo.req.DeptEditReqVO;
import cn.codewoo.vo.req.SysDeptAddReqVO;
import cn.codewoo.vo.resp.DeptRespNodeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kehong
 * 部门服务层实现类
 */
@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired(required = false)
    private SysDeptMapper deptMapper;
    @Autowired
    private RedisService redisService;
    @Override
    public List<SysDept> selectAll() {
        List<SysDept> result = deptMapper.selectAll();
        if (!result.isEmpty()){
            for (SysDept dept : result) {
                SysDept parent = deptMapper.selectByPrimaryKey(dept.getPid());
                if (parent != null){
                    dept.setPidName(parent.getName());
                }
            }
        }
        return result;
    }

    @Override
    public List<DeptRespNodeVO> deptTreeList() {
        List<SysDept> all = deptMapper.selectAll();
        List<DeptRespNodeVO> list = new ArrayList<>();
        List<DeptRespNodeVO> tree = getTree(all);
        return tree;
    }

    @Override
    public SysDept insert(DeptAddReqVO vo) {

        return null;
    }

    @Override
    public int addDept(SysDeptAddReqVO vo) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(vo,sysDept);
        long deptNoCount = redisService.incrby(Constant.DEPT_CODE_KEY, 1);
        String deptNo = Constant.DEPT_NO_PREFIX + CommonUtils.padRight(String.valueOf(deptNoCount), 6, "0");
        sysDept.setId(CommonUtils.generateUUID());
        sysDept.setDeptNo(deptNo);
        //如果上级部门是顶级部门，则层级部门id为当前部门id
        if ("0".equals(vo.getPid())){
            sysDept.setRelationCode(deptNo);
        }else{
            //不是顶级部门，查询上级部门信息
            SysDept parent = deptMapper.selectByPrimaryKey(vo.getPid());
            if (parent == null) {
                //没有上级部门信息，抛出异常
                throw new BusinessException();
            }else{
                sysDept.setRelationCode(parent.getRelationCode() + deptNo);
            }
        }
        int row = deptMapper.insertSelective(sysDept);
        return row;
    }

    @Override
    public SysDept selectById(String id) {
        SysDept sysDept = deptMapper.selectByPrimaryKey(id);
        return sysDept;
    }

    @Override
    public int editDept(DeptEditReqVO vo) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(vo,sysDept);
        sysDept.setUpdateTime(new Date());
        int row = deptMapper.updateByPrimaryKeySelective(sysDept);
        return row;
    }

    private List<DeptRespNodeVO> getTree(List<SysDept> all){
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept dept : all) {
            if ("0".equals(dept.getPid())) {
                DeptRespNodeVO vo = new DeptRespNodeVO();
                BeanUtils.copyProperties(dept,vo);
                vo.setParentName("顶级部门");
                vo.setParentName(dept.getName());
                vo.setParentId(dept.getId());
                vo.setChildren(getChildren(dept,all));
                list.add(vo);
            }
        }
        return list;
    }

    private List<DeptRespNodeVO> getChildren(SysDept parentDept,List<SysDept> all){
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept dept : all) {
            if (parentDept.getId().equals(dept.getPid())){
                DeptRespNodeVO vo = new DeptRespNodeVO();
                BeanUtils.copyProperties(dept,vo);
                vo.setParentName(parentDept.getName());
                vo.setParentId(dept.getId());
                vo.setChildren(getChildren(dept,all));
                list.add(vo);
            }
        }
        return list;
    }
}
