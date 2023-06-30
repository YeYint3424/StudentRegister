//package com.studentsp.studentboot.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.studentsp.studentboot.services.UserServices;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserServices userServices;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//            .antMatchers("/").permitAll() // Allow access to home page
//            .antMatchers("/signup").permitAll() // Allow access to signup page
//            .antMatchers("/user_signup").permitAll() // Allow access to user signup endpoint
//            .anyRequest().authenticated() // Require authentication for any other endpoint
//            .and()
//            .formLogin()
//                .loginPage("/login") // Custom login page URL
//                .defaultSuccessUrl("/user/home") // Redirect to home page after successful login
//                .permitAll()
//            .and()
//            .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Custom logout URL
//                .logoutSuccessUrl("/login") // Redirect to login page after successful logout
//                .permitAll();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userServices).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}