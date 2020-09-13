package cn.codewoo.config;

import cn.codewoo.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.ApiSelector;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    @Value("${swagger.enable}")
//    private String enable;

    /**
     * 创建ApiInfo，封装基本信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfo("JWT整合", "spring boot+shiro+mybatis+redis整合", "1.0", "https://www.codewoo.cn",
                DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    }
    @Bean
    public Docket docket(){
        //存放参数信息的list
        List<Parameter> parameters = new ArrayList<>();
        //参数的建造者对象
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        /**
         * 构建一个参数：
         * naem：参数名称
         * description：参数简介
         * modelRef：参数类型，传入一个Modelref的实例，Modelref有构造方法，直接传入参数的类型包装类名
         * parameterType：参数在request中的类型
         * retuired：是否是必要的
         */
        Parameter authentication = parameterBuilder.name(Constant.ACCESS_TOKEN).description("自测传入AccessToken入口")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        Parameter refreshToken = parameterBuilder.name("refreshToken")
                .description("自测refreshToken入口")
                .required(false)
                .parameterType("header")
                .modelRef(new ModelRef("string")).build();
        parameters.add(authentication);
        parameters.add(refreshToken);
        //创建Docket实例
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //通过建造者方法创建Docket实例并返回
        return docket.groupName("kehong")
                .apiInfo(apiInfo())
                .enable(true)
                .globalOperationParameters(parameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.codewoo.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
