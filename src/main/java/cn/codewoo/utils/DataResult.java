package cn.codewoo.utils;

import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.exception.code.IResponseCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataResult<T> {
    /**
     * 响应状态码
     */
    @ApiModelProperty("响应状态码")
    private int code;
    /**
     * 响应提示语
     */
    @ApiModelProperty("响应提示语")
    private String msg;
    /**
     * 响应的数据
     */
    @ApiModelProperty("响应的数据")
    private T data;


    /**
     * 全参构造
     * @param code
     * @param msg
     * @param data
     */
    public DataResult(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResult(int code,String msg){
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public DataResult(int code,T data){
        this.code = code;
        this.data = data;
        this.msg = null;
    }

    /**
     * 无参构造，默认响应一个成功的不带数据的对象
     */
    public DataResult(){
        this.code = BaseResponseCodeImpl.SUCCESS.getCode();
        this.msg = BaseResponseCodeImpl.SUCCESS.getMsg();
        this.data = null;
    }

    /**
     * 一个默认的成功，带数据的响应
     * @param data
     */
    public DataResult(T data){
        this.code = BaseResponseCodeImpl.SUCCESS.getCode();
        this.msg = BaseResponseCodeImpl.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * 传入一个响应码的实现类，可以通过共有的方法获取到信息
     */

    public DataResult(IResponseCode responseCode){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = null;
    }

    /**
     * 有数据的自定义响应
     */

    public DataResult(IResponseCode responseCode,T data){
        this.data = data;
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    /**
     * 获取默认成功，没有数据的响应实例
     */

    public static DataResult success(){
        return new DataResult<>();
    }

    /**
     * 获取默认成功，有数据的响应实例
     */
    public static <T> DataResult success(T data){
        return new DataResult(data);
    }

    /**
     * 自定义带返回数据响应实例
     */
    public static <T> DataResult getDataResult(int code,String msg,T data){
        return new DataResult(code,msg,data);
    }

    /**
     * 自定义没有返回数据的响应实例
     */
    public static DataResult getDataResult(int code,String msg){
        return new DataResult(code,msg);
    }

    /**
     * 自定义使用响应码枚举获取响应实例，没有返回数据
     */

    public static DataResult getDataResult(IResponseCode responseCode){
        return new DataResult(responseCode);
    }

    /**
     * 自定义使用响应码枚举获取响应数据，含有返回数据
     */
    public static <T> DataResult getDataResult(IResponseCode responseCode,T data){
        return new DataResult(responseCode,data);
    }

}
