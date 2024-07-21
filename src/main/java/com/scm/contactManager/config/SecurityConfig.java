package com.scm.contactManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.contactManager.services.impl.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {

        @Autowired
        private SecurityCustomUserDetailsService userDetailsService;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
            
            httpSecurity.authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/user/**").authenticated();   // authenticate these urls
                authorize.anyRequest().permitAll();                      // permit all other
            });

            // httpSecurity.formLogin(Customizer.withDefaults()); // Using default Login Page

            httpSecurity.formLogin(formLogin -> {

                formLogin.loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("email")
                .passwordParameter("password")
                .successForwardUrl("/user/dashboard");

            });

            //TODO: enable it later
            httpSecurity.csrf(AbstractHttpConfigurer::disable);

            return httpSecurity.build();
        }

        


        @Bean
        public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

            daoAuthenticationProvider.setUserDetailsService(userDetailsService);
            daoAuthenticationProvider.setPasswordEncoder(encoder());

            return daoAuthenticationProvider;
        }

        @Bean
        public PasswordEncoder encoder(){
                return new BCryptPasswordEncoder();
        }


}
