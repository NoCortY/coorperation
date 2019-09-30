package com.objectspace.coorperation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@MapperScan("com.objectspace.coorperation.dao")
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class CoorperationApplication {
    public static void main(String[] args) {SpringApplication.run(CoorperationApplication.class, args);}
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true); // resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxInMemorySize(200 * 1024 * 1024);
        resolver.setMaxUploadSize(100 * 1024 * 1024);// 上传文件大小 5M 5*1024*1024
        return resolver;
    }
}
