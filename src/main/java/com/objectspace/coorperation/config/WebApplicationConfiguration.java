package com.objectspace.coorperation.config;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
* @Description: 解决 Not allowed to load local resource 问题，配置虚拟路径映射
* @Author: NoCortY
* @Date: 2019/10/7
*/
@Configuration
public class WebApplicationConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * @Description: 对文件的路径进行配置, 创建一个虚拟路径/Path/** ，即只要在< img src="/Path/picName.jpg" />便可以直接引用图片
         *          这是图片的物理路径  "file:/+本地图片的地址"
         * @Param: [registry]
         * @return: void
         * @Author: NoCortY
         * @Date: 2019/10/7
         */
        registry.addResourceHandler("/userprofile/**").addResourceLocations("file:C:/NoCortY_Pad/WorkSpace/userprofile/**");
    }
}
