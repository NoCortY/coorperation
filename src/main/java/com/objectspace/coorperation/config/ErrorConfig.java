package com.objectspace.coorperation.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/** 
* @Description: Spring Boot 错误页面配置类
* @Author: NoCortY
* @Date: 2019/10/7
*/
@Configuration
public class ErrorConfig {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            /**
             * @Description:  404 500配置
             * @Param: [factory]
             * @return: void
             * @Author: NoCortY
             * @Date: 2019/10/7
             */
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
                factory.addErrorPages(errorPage404);
                ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500");
                factory.addErrorPages(errorPage500);
            }
        };
    }
}
