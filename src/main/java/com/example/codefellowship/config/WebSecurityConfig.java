package com.example.codefellowship.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailsServiceImplement userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                    // Routes that match these 4 strings, allow anyone.
                    .antMatchers("/", "/login", "/signup", "/error").permitAll()
                    // A POST request to routes that match users, allow anyone.
                    .antMatchers(HttpMethod.POST, "/users").permitAll()
                    // Any other request, you have to be logged in.
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                .and()
                .logout();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}

