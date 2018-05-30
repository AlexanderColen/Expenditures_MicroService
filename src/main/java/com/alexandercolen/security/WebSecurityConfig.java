package com.alexandercolen.security;

//package com.fontys.capitaselecta.security;
//
//import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// *
// * @author Alex
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${auth0.audience}")
//    private String audience;
//
//    @Value("${auth0.issuer}")
//    private String issuer;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//            .antMatchers("/").permitAll()
//            .antMatchers("/expenditures").authenticated();
//
//        JwtWebSecurityConfigurer
//            .forRS256(audience, issuer)
//            .configure(http);
//    }
//}