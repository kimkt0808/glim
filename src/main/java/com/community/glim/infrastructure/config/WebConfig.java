package com.community.glim.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS(Cross-Origin Resource Sharing) 설정을 위한 클래스
 *
 * 클라이언트와 서버 같의 크로스-도메인 요청을 허용하는 정책을 정의한다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * CORS 정책을 설정하는 함수
     *
     * 서버가 어떤 도메인에서 오는 요청을 허용할지, 어떤 HTTP 메서드를 허용할지 등을 정의한다.
     * 기본적으로, 클라이언트와 서버가 서로 다른 도메인에 있을 때 발생할 수 있는 CORS 오류를 방지하기 위한 설정이다.
     *
     * @param registry - CORS 설정을 등록하는 객체
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
