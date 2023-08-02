package com.example.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yf
 * @create 2023-08-01 20:25
 * 接收yaml配置的配置类
 */

@ConfigurationProperties("doc.swagger")
@Data
public class SwaggerProperties {
    private String title = "模板项目文档";
    private String group = "";
    private String description = "模板项目的api文档";
    private String version = "1.0.0";
    private Contact contact = new Contact();
    private String basePackage = "com.example";
    private List<String> basePath = new ArrayList<>();
    private List<String> excludePath = new ArrayList<>();
    private Map<String,DocketInfo> docket = new LinkedHashMap<>();

    public String getGroup(){
        if(group == null || "".equals(group)){
            return title;
        }
        return group;
    }

    @Data
    public static class DocketInfo{
        private String title = "模板项目文档";
        private String group = "";
        private String description = "模板项目的api文档";
        private String version = "1.0.0";
        private Contact contact = new Contact();
        private String basePackage = "com.example";
        private List<String> basePath = new ArrayList<>();
        private List<String> excludePath = new ArrayList<>();
        private Map<String,DocketInfo> docket = new LinkedHashMap<>();
        public String getGroup(){
            if(group == null || "".equals(group)){
                return title;
            }
            return group;
        }

    }

    @Data
    public static class Contact{
        private String name = "chen";
        private String url = "";
        private String email = "";
    }
}
