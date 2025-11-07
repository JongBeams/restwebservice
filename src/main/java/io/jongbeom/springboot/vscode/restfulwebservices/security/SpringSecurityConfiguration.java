package io.jongbeom.springboot.vscode.restfulwebservices.security;

//정적 메서드만 import
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SpringSecurityConfiguration {

    // SecurityFilterChain의 기능
    // 모든 URL 보호
    // 인증되지 않은 접근 시 로그인 화면으로 이동
    // 기본 인증 모드
    // CSRF 미사용
    // stateless 무상태 rest api

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //모든 URL 보호
        return http.authorizeHttpRequests(auth->auth //규칙을 설정하는 메서드
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() //메서드를 사용 하는 모든요청을 허용
                .anyRequest().authenticated()) //모든 요청을 허용 .permitAll()
                .httpBasic(withDefaults())
                .sessionManagement(//세션관리
                    session-> session.sessionCreationPolicy
                    (SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))//CORS에서 URL 제한
                .csrf(csrf -> csrf.disable())
                .build();
        //.requestMatchers("/**").permitAll() //spring security 때문에 임시로 모든 경로 호출 허용
        //http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
    }

     // CORS 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // React 앱의 URL 허용
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        // 모든 HTTP 메서드 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 모든 헤더 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // 인증 정보 허용
        configuration.setAllowCredentials(true);
        // 모든 경로에 CORS 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }

}
