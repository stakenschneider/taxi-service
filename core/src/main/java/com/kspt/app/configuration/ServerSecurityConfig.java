//package com.kspt.app.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Created by Masha on 18.03.2020
// */
//@Configuration
//public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication().withUser("john").password("123").roles("USER");
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean()
//            throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll();
//    }
//}
