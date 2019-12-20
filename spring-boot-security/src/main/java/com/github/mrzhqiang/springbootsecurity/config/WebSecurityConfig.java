package com.github.mrzhqiang.springbootsecurity.config;

import com.github.mrzhqiang.springbootsecurity.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public UserDetailsService customUserService() {
    return new CustomUserService();
  }

  @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserService())
        .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
  }

  @Override public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**");
  }

  @Override protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/error")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .failureUrl("/login?error")
        .successForwardUrl("/home")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and().csrf().disable();
  }
}
