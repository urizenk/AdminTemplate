package com.example.config.swagger;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * @author yf
 * @create 2023-07-31 13:22
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableSwagger2
@ConditionalOnProperty(name = "doc.swagger.enabled", havingValue = "true",matchIfMissing = true)
public class SwaggerAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Autowired
    SwaggerProperties swaggerProperties;

    @Bean
    @ConditionalOnMissingBean
    public List<Docket> createRestApi(){
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        List<Docket> dockets = new LinkedList<>();
        if(swaggerProperties.getDocket().isEmpty()){
            //没有分组的情况下
            Docket docket = createDocket(swaggerProperties);
            configurableBeanFactory.registerSingleton(swaggerProperties.getTitle(),docket);
            dockets.add(docket);
        }else {
            Set<String> keySet = swaggerProperties.getDocket().keySet();
            for (String key : keySet) {
                SwaggerProperties.DocketInfo docketInfo = swaggerProperties.getDocket().get(key);
                ApiInfo apiInfo = new ApiInfoBuilder()
                        .title(docketInfo.getTitle())
                        .contact(new Contact(docketInfo.getContact().getName(),
                                docketInfo.getContact().getUrl(),
                                docketInfo.getContact().getEmail()))
                        .description(docketInfo.getDescription())
                        .version(docketInfo.getVersion())
                        .build();
                if (swaggerProperties.getBasePath().isEmpty()) {
                    swaggerProperties.getBasePath().add("/**");
                }
                List<Predicate<String>> basePath = new ArrayList<>();
                for (String path : swaggerProperties.getBasePath()) {
                    basePath.add((Predicate<String>) PathSelectors.ant(path));
                }

                List<Predicate<String>> excludePath = new ArrayList<>();
                for (String path : swaggerProperties.getExcludePath()) {
                    excludePath.add((Predicate<String>) PathSelectors.ant(path));
                }

                Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                        .groupName(swaggerProperties.getGroup()) //一个实例配置一个组名，代表着一个api分组
                        .apiInfo(apiInfo) //设置api信息
                        .select()
                        .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())) //配置扫描的接口的方式与范围
                        .paths(Predicates.and(Predicates.not(Predicates.or(excludePath)),Predicates.or(basePath)))
                        .build();
                configurableBeanFactory.registerSingleton(key,docket);
                dockets.add(docket);
            }
        }
        return dockets;
    }


    private ApiInfo apiInfo(SwaggerProperties swaggerProperties){
        return new ApiInfoBuilder()
                //标题
                .title(swaggerProperties.getTitle())
                //服务条款
                .termsOfServiceUrl("暂无服务条款")
                //描述
                .description(swaggerProperties.getDescription())
                //版本
                .version(swaggerProperties.getVersion())
                //联系人信息
                .contact(new Contact(swaggerProperties.getContact().getName(),
                        swaggerProperties.getContact().getUrl(),
                        swaggerProperties.getContact().getEmail()))
                //开源协议
                .license("Apache 2.0")
                .build();
    }


    private Docket createDocket(SwaggerProperties swaggerProperties) {
        ApiInfo apiInfo = apiInfo(swaggerProperties);
        if (swaggerProperties.getBasePath().isEmpty()) {
            swaggerProperties.getBasePath().add("/**");
        }
        List<Predicate<String>> basePath = new ArrayList<>();
        for (String path : swaggerProperties.getBasePath()) {
            basePath.add((Predicate<String>) PathSelectors.ant(path));
        }

        List<Predicate<String>> excludePath = new ArrayList<>();
        for (String path : swaggerProperties.getExcludePath()) {
            excludePath.add((Predicate<String>) PathSelectors.ant(path));
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerProperties.getGroup()) //一个实例配置一个组名，代表着一个api分组
                .apiInfo(apiInfo) //设置api信息
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())) //配置扫描的接口的方式与范围
                .paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath)))
                .build();
//                .protocols("https","http") //配置协议
//                .globalRequestParameters()
//                .globalResponses()

    }
}
