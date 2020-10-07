package cn.codewoo.mapper;

import cn.codewoo.entity.SysDept;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    /**
     * 查询所有记录
     * @return
     */
    List<SysDept> selectAll();
}