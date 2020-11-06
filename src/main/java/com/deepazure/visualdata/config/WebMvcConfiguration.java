package com.deepazure.visualdata.config;

import com.deepazure.visualdata.interceptor.AccessTokenInterceptor;
import com.deepazure.visualdata.service.UserService;
import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final UserService userService;

    public WebMvcConfiguration(UserService userService) {
        this.userService = userService;
    }

    /**
     * Spring Boot 2.x 的配置方式
     * 重写 addCorsMappings 的方式配置跨域已经过时了，不再起作用
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        var config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setMaxAge(3600L);
        config.setAllowCredentials(true);
        var configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessTokenInterceptor(userService))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/users/login", "/users/register", "/users/info",
                        "/401", "/403",
                        "/500", "/404",
                        "/error", "/");
    }
}
