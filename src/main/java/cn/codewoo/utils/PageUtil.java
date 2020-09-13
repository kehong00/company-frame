package cn.codewoo.utils;

import cn.codewoo.vo.resp.PageRespVO;
import com.github.pagehelper.Page;

import java.util.List;

public class PageUtil {
    //私有化构造方法，只能使用提供的工具静态方法
    private PageUtil(){}
    //创建构造page响应对象的方法
    public static <T>PageRespVO<T> getPageRespVO(List<T> list){
        PageRespVO.PageRespVOBuilder<T> builder = PageRespVO.builder();
        //判断传入的list是不是Page的父类
        if (list instanceof Page){
            Page<T> page = (Page<T>) list;
            builder.totalRows(page.getTotal())
                    .totalPages(page.getPages())
                    .pageNum(page.getPageNum())
                    .pageSize(page.getPageSize())
                    .curPageSize(page.size())
                    .list(page.getResult());
        }
        return builder.build();
    }
}
