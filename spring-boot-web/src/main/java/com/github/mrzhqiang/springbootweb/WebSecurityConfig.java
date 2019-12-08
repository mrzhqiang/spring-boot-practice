package com.github.mrzhqiang.springbootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/chat")
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }

  @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder)
        .withUser("wyf")
        .password(passwordEncoder.encode("wyf"))
        .roles("USER")
        .and()
        .withUser("wisely")
        .password(passwordEncoder.encode("wisely"))
        .roles("USER");
  }

  @Override public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/static/**");
  }
}
