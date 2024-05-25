package com.scmvivek.config;

// import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.AuthenticationFailureHandler;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scmvivek.services.impl.SecurityCustomUserDetailService;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;


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
    
    @Autowired
    private OAuthAuthenticationSuccessHandler handler;
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
        httpSecurity.formLogin(formLogin -> {
            
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate"); //when we submit the form it will go to this url
            formLogin.successForwardUrl("/user/dashboard"); // It will redirect to this url after successful login
            //formLogin.failureForwardUrl("/login?error=true");

            formLogin.usernameParameter("email"); // It will take the email as username
            formLogin.passwordParameter("password");
            
            // formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     // This will run when the authentication fails
            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // 
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // 
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            // });
            
        }); // It will enable the form login for /user/profile etc.
       
    httpSecurity.csrf(AbstractHttpConfigurer::disable); // It will disable the csrf token (Cross Site Request Forgery) we can do get request of logout
        httpSecurity.logout(logoutForm->{
        logoutForm.logoutUrl("/logout");
        logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        //Oauth Configuration
        httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        
        });
        
        // It will enable the oauth2 login
        return httpSecurity.build(); // build() It will return default security filter chain
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // It will encode the password
    }
}
