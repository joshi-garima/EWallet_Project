package com.majorProject.UserService.config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Value;

// import org.hibernate.usertype.UserType;

@Configuration
public class SecurityConfig {

    @Value("$(user.Authority)")
    private String user;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/addUpdate/**").permitAll()
                        .requestMatchers("/userTxn/start-txn/**").hasAuthority(user)
                        .anyRequest().authenticated()
                ).formLogin(withDefaults())
                .httpBasic(withDefaults()).csrf(csrf ->csrf.disable());
        return http.build();
    }
    
    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
