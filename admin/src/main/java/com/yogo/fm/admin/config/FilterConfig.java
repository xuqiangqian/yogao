package com.yogo.fm.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
@Configuration
public class FilterConfig {

    /**
     * 解决跨域问题
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //放行哪些原始域，比如"http://domain1.com,https://domain2.com"
        config.addAllowedOrigin("*");
        // 放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        //拦截所有的url
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


}
