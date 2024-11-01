package com.ohgiraffers.chap10websocketreact.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig  implements WebMvcConfigurer {
    //특정 ip에 요청허용
//@Configuration: Spring에서 이 클래스가 설정 클래스임을 나타냅니다. 애플리케이션 시작 시 이 설정이 적용됩니다.

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    // addMapping("/**"): 모든 URL 경로에 대해 CORS 요청을 허용합니다. 예를 들어, "/api/**"로 제한하면 특정 경로만 허용할 수도 있습니
        registry.addMapping("/**")//모든 요청에대해
                .allowedOrigins("http://localhost:3000") //http://localhost:3000에서 오는 요청을 허용합니다.
                .allowedMethods("GET", "POST", "PUT", "DELETE") //허용할 HTTP메소드
                .allowedHeaders("*"); //허용할 헤더

         }
}
