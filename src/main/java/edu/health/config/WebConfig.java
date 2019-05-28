package edu.health.config;

import edu.health.core.interceptor.ConsoleInterceptor;
import edu.health.core.interceptor.ParamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 执笔
 * @date 2019/5/22 14:38
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ParamInterceptor   paramInterceptor;
    @Autowired
    private ConsoleInterceptor consoleInterceptor;


    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(paramInterceptor).addPathPatterns("/**");
        registry.addInterceptor(consoleInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/add")
                .excludePathPatterns("/Css/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/Js/**")
                .excludePathPatterns("/Images/**")
                .excludePathPatterns("/IMG/**")
                .excludePathPatterns("/assets/**")
                .excludePathPatterns("/verify")
                .excludePathPatterns("/login");
    }


}
