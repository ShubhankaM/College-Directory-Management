package com.college.CollegeDirectory_Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/css/*", "/js/*").permitAll()
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/faculty/**").hasRole("FACULTY_MEMBER")
                .antMatchers("/admin/**").hasRole("ADMINISTRATOR")
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("student").password("{noop}password").roles("STUDENT")
            .and()
            .withUser("faculty").password("{noop}password").roles("FACULTY_MEMBER")
            .and()
            .withUser("admin").password("{noop}password").roles("ADMINISTRATOR");
    }
}