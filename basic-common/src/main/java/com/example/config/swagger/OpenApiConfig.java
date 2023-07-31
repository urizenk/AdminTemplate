package com.example.config.swagger;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author yf
 * @create 2023-07-31 13:22
 */
@Configuration
@EnableOpenApi
@EnableSwaggerBootstrapUI
public class OpenApiConfig {

    /**
     * openapi的knife4j扩展
     */
    private final OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * 自动注入这个解析器
     */
    @Autowired
    public OpenApiConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket openApi(Environment environment){
        String groupName = "123dot";
        return new Docket(DocumentationType.OAS_30)
                .groupName(groupName) //一个实例配置一个组名，代表着一个api分组
                .apiInfo(apiInfo()) //设置api信息
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //配置扫描的接口的方式与范围
                .paths(PathSelectors.any())
                .build()
//                .protocols("https","http") //配置协议
//                .globalRequestParameters()
//                .globalResponses()
                .extensions(openApiExtensionResolver.buildExtensions(groupName)) //加载knife4j的扩展
                .extensions(openApiExtensionResolver.buildSettingExtensions())
                .enable(isOpen(environment));
    }

    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //标题
                .title("后台管理项目模板的标题")
                //服务条款
                .termsOfServiceUrl("暂无服务条款")
                //描述
                .description("后台管理项目模板的描述")
                //版本
                .version("1.0.0")
                //联系人信息
                .contact(new Contact("123dot","https://123dot.com","kot123778@163.com"))
                //开源协议
                .license("Apache 2.0")
                .build();
    }

    /**
     * 判断环境是否开启swagger
     * @param environment
     * @return
     */
    private boolean isOpen(Environment environment){
        Profiles profiles = Profiles.of("dev", "test");
        return environment.acceptsProfiles(profiles);
    }
}
