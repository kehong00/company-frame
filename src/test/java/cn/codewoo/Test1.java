package cn.codewoo;

import io.jsonwebtoken.lang.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test1 {
    @Test
    void test1(){
        char[] chars = new char[]{'1','3','伍'};
    }

    @Test
    void test2(){
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(format);
    }
}
