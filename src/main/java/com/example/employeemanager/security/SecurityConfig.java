package com.example.employeemanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //authorization

//    @Bean
//    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)                                   //CSRF disabled
//                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        authorizationManagerRequestMatcherRegistry
//                                .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
//                                .requestMatchers("/home").permitAll()
//                                .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                                .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults())
//                .build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)                             //disabling CSRF
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/employee/**", "/css/**", "/js/**").permitAll()
                                .requestMatchers("employee/hi").permitAll()          //hi for all
                                .requestMatchers("employee/admin-dashboard").hasAnyRole("ADMIN")         //admin can access only
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())                  // enables the use of a login form to authenticate users.
                .logout(Customizer.withDefaults())
                .build();
    }


    //Authentication
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {

        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }







}
