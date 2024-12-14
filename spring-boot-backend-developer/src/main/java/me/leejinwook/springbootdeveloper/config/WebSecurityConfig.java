package me.leejinwook.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.leejinwook.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userService;

    /*
     스프링 시큐리티 기능 비활성화
     일반적으로 정적 리소스에 설정함 ( + h2-console 하위 url)
     */
    @Bean
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    /*
     특정 HTTP 요청에 대한 웹 기반 보안 구성
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests(auth -> auth // 인증, 인가 설정
                        .requestMatchers(new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/user"))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/login") // 폼 기반 로그인 설정
                        .defaultSuccessUrl("/articles"))
                .logout(logout -> logout.logoutSuccessUrl("/login") // 로그아웃 설정
                        .invalidateHttpSession(true))
                .csrf(AbstractHttpConfigurer::disable) // 비활성화 (편한 실습을 위해서)
                .build();
    }
    /*
     requestMatchers() : 특정 요청과 일치하는 url에 대한 엑세스를 설정한다.
     permitAll() : 누구나 접근이 가능하게 설정한다. 즉, "/login", "/signup", "/user"로 요청이 오면 인증/인가 없이도 접근할 수 있다.
     anyRequest() : 위에서 설정한 url 이외에 요청에 대해 설정
     authenticated() : 인가 여부 안보고 인증이 성공됬을 때만
     loginPage() : 로그인 페이지 경로 설정
     defaultSuccessUrl() : 로그인 완료시 이동할 경로
     logoutSuccessUrl() : 로그아웃 완료시 이동할 경로
     invalidateHttpSession() : 로그아웃 이후 세션을 전체 삭제할지 여부
     */

    /*
     인증 관리자 관련 설정
     사용자 정보를 가져올 서비스 재정의, 인증 방법, LDAP, JDBC 기반 인증 등의 설정
     */
    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailService userDetailService
    ) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); // 사용자 정보 서비스 설정
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }
    /*
     setUserDetailsService() : 사용자 정보를 가져올 서비스 설정. 이때 설정하는 서비스 클래스는 반드시 UserDetailsService를 상속받은 경우만 가능
     setPasswordEncoder() : 비밀번호 암호화 인코더 설정
     */

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
