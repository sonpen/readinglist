package com.manning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Created by 1109806 on 2018-07-09.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")   // READER 권한 필요
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")    // 로그인 폼 경로 설정
                .failureUrl("/login?error=true");
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {   // 사용자 정의 UserDetailService
            @Override
            public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {

                System.out.println("======>" + username);
                return readerRepository.findOne(username);
            }

        });
    }
    */
}
