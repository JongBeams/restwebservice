package io.jongbeom.springboot.vscode.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }

    // http://localhost:3000/ 에서 8080으로
    // 서로 다른 도메인/포트 사이 요청 = CORS 상황, CORS(Cross-Origin Resource Sharing) 브라우저 보안 정책
    // 제한
    // http://localhost:3000/ 에서의 요청만을 허용
    // Allow * from

    //CORS Mapping
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:3000");
            }
        };
    }

}
