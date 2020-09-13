package cn.codewoo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(value = "spring.datasource.type",havingValue = "com.alibaba.druid.pool.DruidDataSource")
    @ConditionalOnClass(DruidDataSource.class)
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    /*@Bean
    public ServletRegistrationBean stateViewServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet());
        Map<String,String> map = new HashMap<>();
        map.put(StatViewServlet.PARAM_NAME_USERNAME,"admin");
        map.put(StatViewServlet.PARAM_NAME_PASSWORD,"666666");
        registrationBean.setInitParameters(map);
        return registrationBean;
    }*/

    /*@Bean
    public FilterRegistrationBean WebStatFilterBean(){
        FilterRegistrationBean webStatFilter = new FilterRegistrationBean(new WebStatFilter());
        Map<String,String> map = new HashMap<>();
        map.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/**,*.jpg,*.png");
        webStatFilter.setInitParameters(map);
        webStatFilter.addUrlPatterns("/**");
        return webStatFilter;
    }*/

    /**
     * 注册StatViewServlet，Druid监控的Servlet
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> servletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> map = new HashMap<>();
        //访问的用户名密码
        map.put(StatViewServlet.PARAM_NAME_USERNAME, "root");
        map.put(StatViewServlet.PARAM_NAME_PASSWORD, "root");
        //允许访问的ip，默认是所有ip
        map.put(StatViewServlet.PARAM_NAME_ALLOW, "");
        //禁止访问的ip
        map.put(StatViewServlet.PARAM_NAME_DENY, "192.168.1.1");
        bean.setInitParameters(map);
        return bean;
    }

    /**
     * 配置一个监控的filter
     *
     * @return WebStatFilter
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String, String> map = new HashMap<>();
        //移除这些监听
        map.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*,*.gif,*.jpg,*.png");
        bean.setInitParameters(map);
        //拦截所有请求，全部都要走druid监听
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }

}
