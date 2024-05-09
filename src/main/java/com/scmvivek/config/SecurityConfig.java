package com.scmvivek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



@Configuration
public class SecurityConfig {
    
    // @Bean
    // public UserDetailsService userDetailsService(){ // Spring security uses UserDetailsService interface to fetch user details
        
    //     UserDetails user1 = User
    //     .withDefaultPasswordEncoder()  //This is not for Production use it is only for testing purpose
    //     .username("admin123")
    //     .password("admin123")
    //     .roles("ADMIN","USER") //It will give role to the user
    //     .build(); //user create and login using java code with in memory service it will directly pick username and password from database

    //     UserDetails user2 = User
    //     .withDefaultPasswordEncoder()
    //     .username("user123")
    //     .password("password")
    //     // .roles(null)
    //     .build();

    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2); //It will store the user details in memory so we will not use this in production we will use database user
    //     return  inMemoryUserDetailsManager;
    // }
}
