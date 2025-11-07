package io.jongbeom.springboot.intellij.restfulwebservices.security;

//정적 메서드만 import
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    //SecurityFilterChain의 기능
    //모든 URL 보호
    //인증되지 않은 접근 시 로그인 화면으로 이동
    //CSRF 미사용
    //Frames
    @Bean
    //Http ServletRequest에 매칭될 수 있는 필터체인을 정의
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {//웹 기반 보안을 설정하도록 해준다. 기본값으로서 이건 모든 요청에 정용될 것이다.
        //모든 URL 보호
        http.authorizeHttpRequests( //규칙을 설정하는 메서드
                auth->auth.anyRequest().authenticated());  //모든 요청을 인증된 사용자만 사용가능하도록 정의
        http.httpBasic(withDefaults());
        // http.formLogin(withDefaults()); 와의 차이 - Basic: 브라우저 기본 팝업 / Login: 커스텀 로그인 페이지
        //httpBasic: API 개발, 간단한 인증, Stateless
        //formLogin: 웹 앱, 사용자 친화적, Stateful


        //보안관련 스프링 세큐리티 버전 6.1이상에서 지원 중단 및 제거 예정
        //http.csrf().disable();
        //http.headers().frameOptions().disable();

        //람다식으로 변환되어 사용
        http.csrf(csrf -> csrf.disable());
        //http.headers(headers -> headers.frameOptions(frame -> frame.disable()));


        return http.build();
    }


}
