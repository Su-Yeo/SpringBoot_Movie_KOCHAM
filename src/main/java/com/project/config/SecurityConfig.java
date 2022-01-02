package com.project.config;

import com.project.member.service.MemberService;
import com.project.store.config.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login") // 1.로그인 페이지 URL 생성
                .defaultSuccessUrl("/")            // 2.로그인 성공 시 이동할 URL
                .usernameParameter("email")        // 3.로그인 시 사용할 파라미터 이름으로 emali을 지정
                .failureUrl("/members/login/error")// 4.로그인 실패 시 이동할 URL을 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                // 5.로그아웃 URL을 설정
                .logoutSuccessUrl("/")// 6.로그아웃 성공 시 이동할 URL
        ;

        http.authorizeRequests() //1.시큐리티 처리에 HttpServletRequest를 이용하겠다는 의미
                .mvcMatchers("/","/members/**",
                        "/item/**","images/**","store/**","movie/**","theater/**",
                        "reservation/**","board/**","admin/team/**").permitAll()
                //2.permitAll()을 통해 모든 사용자가 인증(로그인)없이 해당경로에 접근 가능하게 설정
                //메인페이지. 회원 관련 URL, 상품페이지, 상품 이미지 불러오는 경로등 설정

                .mvcMatchers("/admin/**").hasRole("ADMIN")
                //3. admin 경로는 ADMIN Rile 만 접근 가능하도록 설정
                .mvcMatchers("/adminSchedule/**").hasRole("ADMIN")
                //3. admin 경로는 ADMIN Rile 만 접근 가능하도록 설정
                .anyRequest().authenticated() //4. 위의 2,3을 제외한 모든 경로는 인증을 요구한다.
        ;
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        //5. 인증되지 안은 사용자가 리소스에 접근하였을때 수앵되는 핸들러를 등록
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","js/**","/img/**");//6. static 디렉토리의 하위 파일은 인증을 무시하도록 설정
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
