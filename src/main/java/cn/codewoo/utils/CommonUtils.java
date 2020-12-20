package cn.codewoo.utils;


import cn.codewoo.constant.Constant;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @ClassName CommonUtils
 * @Description 常用工具类封装
 * @Author kehong
 * @Date 2020/11/17 10:45 上午
 * @Version 1.0
 **/
public class CommonUtils {
    /**
     * 生成32位uuid
     * @return
     */
    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","").substring(0,32);
        return uuid;
    }

    public static String MD5(String data){
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 密码匹配
     * @param encPasswd 密文
     * @param rawPasswd 明文
     * @return
     */
    public static Boolean matcher(String encPasswd, String rawPasswd){
        String md5 = MD5(rawPasswd);
        return md5.equals(encPasswd);
    }


    /**
     * 获取请求中的token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request){
        //先从header中获取
        String token = request.getHeader(Constant.ACCESS_TOKEN);
        //如果header中没有
        if (!StringUtils.hasLength(token)){
            //从参数中获取
            token = request.getParameter(Constant.ACCESS_TOKEN);
        }
        return token;
    }

    /**
     * 向字符串左边补位，如果原字符串比需求的长度小，则在原字符串左边添加alexin定义的字符
     * @param ordString：原始字符串
     * @param len：需求字符串的长度
     * @param alexin：补位占用的字符
     * @return：返回得到的字符串
     */
    public static String padRight(String ordString,int len,String alexin){
        StringBuffer s = new StringBuffer("");
        //需求长度-实际长度得到的差，如果>0则需要补位，差值就是需要的补位的长度
        for (int i = 0; i < len - ordString.length(); i++) {
            s.append(alexin);
        }
        return s + ordString;
    }


}
