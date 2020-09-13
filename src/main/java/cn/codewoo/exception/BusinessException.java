package cn.codewoo.exception;

import cn.codewoo.exception.code.IResponseCode;

public class BusinessException extends RuntimeException {
    private int code;
    private String msg;

    public BusinessException() {
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(IResponseCode responseCode){
        this(responseCode.getCode(),responseCode.getMsg());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
