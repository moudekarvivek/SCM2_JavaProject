package com.scmvivek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scmvivek.services.impl.SecurityCustomUserDetailService;


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
    @Autowired
    private SecurityCustomUserDetailService userDetailService; 
    // Now we want to authenticate the user using database. to tell userdetails we will provide DAOAuthenticationProvider
   
    // Configuration of AuthenticationProvider for spring security
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); // This DaoAuthenticationProvider have all the methods with help of which we can register our service
        // Object of user detail service
        daoAuthenticationProvider.setUserDetailsService(userDetailService); // It will tell the userdetails service that we are using database to authenticate the user
        // password encoder Object we will provide here
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // It will encode the password
        
        return daoAuthenticationProvider;
    }
    
    @Bean // It will create a bean of SecurityFilterChain
    public SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity) throws Exception{
       //Configuration
       // we have configured the URL's which will be public and which will be private
       
       httpSecurity.authorizeHttpRequests(authorize->{
            // authorize.requestMatchers("/about","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated(); // It will allow all the url starting with /user
            authorize.anyRequest().permitAll(); // It will allow all the request
        });
        
        //Form default login
        // If we want to cutomize anything we will come here: related to form login
        httpSecurity.formLogin(Customizer.withDefaults()); // It will enable the form login for /user/profile etc.
       
       return httpSecurity.build(); // build() It will return default security filter chain
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // It will encode the password
    }
}
