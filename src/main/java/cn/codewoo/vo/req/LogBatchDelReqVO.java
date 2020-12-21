package cn.codewoo.vo.req;

import java.util.List;

/**
 * @ClassName LogBatchDelReqVO
 * @Description 批量删除日志记录请求vo
 * @Author kehong
 * @Date 2020/12/20 9:07 下午
 * @Version 1.0
 **/
public class LogBatchDelReqVO {
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "LogBatchDelReqVO{" +
                "ids=" + ids +
                '}';
    }
}
