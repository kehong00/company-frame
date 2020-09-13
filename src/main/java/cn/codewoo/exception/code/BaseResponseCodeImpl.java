package cn.codewoo.exception.code;

public enum  BaseResponseCodeImpl implements IResponseCode {
    SUCCESS(0,"操作成功"),
    ERROR(-1,"操作失败"),
    DATA_ERROR(4000001,"传入的数据异常"),
    VALIDATOR_ERROR(4000002,"参数校验异常"),
    SYS_ERROR(500001,"系统错误"),
    ACCOUNT_NOTFOUND(400004,"用户不存在"),
    ACCOUNT_LOCK_TIP(400003,"账号已被锁定"),
    ACCOUNT_PASSWORD_ERROR(400005,"密码不正确"),
    ACCOUNT_LOCK(4010001,"该账号已被锁定"),
    ACCOUNT_HAS_DELETE_ERROR(4010001,"账户已被删除"),
    TOKEN_NOT_NULL(4010002,"认证凭证不能为空，请重新登录"),
    TOKEN_ERROR(4010002,"身份认证异常，请重新登录"),
    NOT_PERMISSION(4030001,"没有权限")
    ;
    private final int code;
    private final String msg;

    BaseResponseCodeImpl(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
