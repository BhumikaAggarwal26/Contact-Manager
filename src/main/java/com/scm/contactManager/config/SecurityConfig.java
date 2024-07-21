package com.scm.contactManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.contactManager.services.impl.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {

        @Autowired
        private SecurityCustomUserDetailsService userDetailsService;

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
