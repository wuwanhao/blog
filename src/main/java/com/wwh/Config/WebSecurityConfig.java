package com.wwh.Config;

import com.wwh.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
/*@EnableWebSecurity*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AuthService authService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return this.authService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring()
                .antMatchers("static/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();

        //定制请求的授权规则
        http.authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .successForwardUrl("/admin/index")
                .loginProcessingUrl("/login")
                .permitAll();

        //路由权限设置
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/lib/**").permitAll()
                .antMatchers("/admin/**").hasRole("USER");          // 需要有USER角色才可以登录到后台管理页面

        // 退出登录
        http.authorizeRequests()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }


}
