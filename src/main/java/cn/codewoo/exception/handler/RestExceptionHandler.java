package cn.codewoo.exception.handler;

import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler{
    @ExceptionHandler(Exception.class)
    public <T> DataResult<T> handleException(Exception e){
        log.error("发生系统错误{}",e);
        return DataResult.getDataResult(BaseResponseCodeImpl.SYS_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public DataResult handleException(BusinessException e){
        log.error("BusinessException.{}",e);
        return DataResult.getDataResult(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataResult handleException(MethodArgumentNotValidException e){
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        log.error("发生数据校验异常:",objectError);
        return DataResult.getDataResult(BaseResponseCodeImpl.VALIDATOR_ERROR.getCode(),objectError.getDefaultMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public DataResult handleException(UnauthorizedException e){
        log.error("权限校验异常：",e);
        return DataResult.getDataResult(BaseResponseCodeImpl.NOT_PERMISSION);
    }
}
