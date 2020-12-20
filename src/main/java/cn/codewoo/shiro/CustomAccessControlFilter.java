package cn.codewoo.shiro;

import cn.codewoo.constant.Constant;
import cn.codewoo.exception.BusinessException;
import cn.codewoo.exception.code.BaseResponseCodeImpl;
import cn.codewoo.utils.CommonUtils;
import cn.codewoo.utils.DataResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author kehong
 */
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try{
            log.info("拦截到的地址是：" + request.getRequestURL());
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
            log.info(request.getHeader(headerNames.nextElement()));
        }
            String token = CommonUtils.getToken(request);
            if (Strings.isEmpty(token)){
                throw new BusinessException(BaseResponseCodeImpl.TOKEN_NOT_NULL);
            }
            //获得主体
            Subject subject = this.getSubject(servletRequest, servletResponse);
            //提交认证
            subject.login(new CustomUsernamePasswordToken(token));
        }catch (BusinessException e){
            this.customResponse(DataResult.getDataResult(e.getCode(),e.getMsg()),servletResponse);
            log.error("发生异常："+e.getMsg(),e);
            return false;
        }catch (AuthenticationException e){
            if (e.getCause() instanceof BusinessException){
                BusinessException businessException = (BusinessException) e.getCause();
                this.customResponse(DataResult.getDataResult(businessException.getCode(),businessException.getMsg()),servletResponse);
                log.error("发生异常："+businessException.getMsg(),e);
                return false;
            }
            this.customResponse(DataResult.getDataResult(BaseResponseCodeImpl.TOKEN_ERROR),servletResponse);
            log.error("发生认证异常",e);
            return false;
        }catch (Exception e){
            this.customResponse(DataResult.getDataResult(BaseResponseCodeImpl.SYS_ERROR),servletResponse);
            log.error("发生系统异常",e);
            return false;
        }
        return true;
    }

    private void customResponse(DataResult dataResult,ServletResponse response){
        try{
            String jsonString = JSON.toJSONString(dataResult);
            response.setCharacterEncoding("utf8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(jsonString.getBytes("utf8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
