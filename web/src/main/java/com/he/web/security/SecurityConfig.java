package com.he.web.security;

import com.he.web.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userSecurityService;

    @Resource
    private JwtAuthenticationTokenFilter    tokenFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置安全信息
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .sameOrigin();

        //允许对于网站静态资源的无授权访问
        http
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/login",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll();

        //基于token，所以不需要session
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 对于获取token的rest api要允许匿名访问
        http
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

//        //页面请求需要授权并设置登录页面
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                //若禁止form提交(ajax)，使用该设置
//                //.disable()
//                //若使用form提交，使用如下配置
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/index");

//        //设置登出方法
//        http
//
//                .logout()
//                .logoutUrl("/logout")
//                .deleteCookies("JSESSIONID")
//                .permitAll();

        // 添加JWT filter
        http
                //在UsernamePasswordAuthenticationFilter 前添加 JwtAuthenticationTokenFilter
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        //禁用缓存
        http.headers().cacheControl();
    }

    @Override
    //@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//数据库校验
        auth
                .userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());

        //内存校验
//		auth
//				.inMemoryAuthentication()
//				.withUser("admin").password("admin123").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

}
