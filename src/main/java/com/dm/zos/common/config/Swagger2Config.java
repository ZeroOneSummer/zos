package com.dm.zos.common.config;

import com.dm.zos.common.r.R;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Administrator
 * @Description: TODO
 * @date 2018/8/14 19:07
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.dm.zos.controller"))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(R.class);

    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("FBI服务")
                //创建人
                .contact(new Contact("ZeroOneSummer", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("FBI服务API描述")
                .build();
    }
}
