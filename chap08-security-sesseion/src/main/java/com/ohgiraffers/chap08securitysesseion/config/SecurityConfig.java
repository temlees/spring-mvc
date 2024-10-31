package com.ohgiraffers.chap08securitysesseion.config;


import com.ohgiraffers.chap08securitysesseion.common.UserRole;
import com.ohgiraffers.chap08securitysesseion.config.handler.AuthFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity //시큐리티 리소스관리 빈들 모아놓은 컨피그레이션
public class SecurityConfig {

    @Autowired
    private AuthFailHandler authFailHandler;

    //비밀번호 인코딩 빈
    @Bean
    public PasswordEncoder passwordEncoder() {
        //비밀번호 알고리즘 메소드
        return new BCryptPasswordEncoder();
    }

    //정적 리소스 요청 제외 bean
    //static 폴더에 들어가는거는 시큐리티에서 뺌

    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //필터체인 커스텀
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->{ //서버의 리소스에 접근 가능한 권한 설정
            //이 요청들은 모든 사용자에게 허용 - 인증 필요 업음
            auth.requestMatchers("/auth/login","user/signup","auth/fail","/").permitAll();
            //Role_admin에게멘 권한 허용
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole());
            //Role_user에게만 허용
            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole());
            auth.anyRequest().authenticated(); // 모든 요청을 인증된 사용자에게 허용해주겠다.
        }).formLogin(login->{
            login.loginPage("/auth/login");
            login.usernameParameter("user");
            login.passwordParameter("pass"); //html input name 값
            login.defaultSuccessUrl("/"); // 로그인 성공시 보낼곳 설정.. mapping이 존재 해야함.
            login.failureHandler(authFailHandler); // 실패시 처리
        }).logout(logout->{
            //로그아웃시 요청을 날릴 url설정  페이지 만들필요 없음... 시큐리티가 처리
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));

            //세션이 만들어지면 쿠키에 JSESSIONID 가있다
            //이걸 지우게 되면 세션 쓰지 못함 (제거)
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);//세션소멸을 허용하는 메소드
            logout.logoutSuccessUrl("/");//로그아웃을 완료후 이동할 페이지 설정
        }).sessionManagement(session->{
            session.maximumSessions(1); // 세션의 갯수제한 1로 설정 시 중복 로그인 x
            session.invalidSessionUrl("/"); //세션 만료시 이동할 페이지
        }).csrf(csrf->csrf.disable()); // csrf 처리 안함

        return http.build();
    }
}
