package com.scmvivek.entities;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users") //It will create a table with name users
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails { // UserDetails is an interface which is used to get the user details or to represent user data
    
    @Id                  //It will make userId as primary key
    private String userId;
    
    @Column(name="user_name",nullable = false) //It will create a column with name user_name and it will not be null
    private String name;
   
    @Column(unique = true,nullable = false) //It will create a column with name email and it will be unique and not null
    private String email;
    
    @Getter(AccessLevel.NONE)
    private String password; // To exclude getter we will use @Getter(AccessLevel.NONE) it will remove getter
    
    @Column(length = 1000) //It will create a column with name about and it will have length 10000
    private String about;
   
    @Column(length = 1000) //It will create a column with name profile_pic and it will have length 10000
    private String profilePic;
    
    private String phoneNumber;
    //Information
   
    private boolean enable=true; // user verified or not
    private boolean emailVerified=false; // email verified or not
    private boolean phoneVerified=false; // phone verified or not

    // SELF,Google,Facebook,twitter,linkedin,github
    @Enumerated(value = EnumType.STRING) //It will store the value of provider in string format
    private Providers provider=Providers.SELF;//By default it will be SELF //An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).
    private String providerUserId;//Google id,Facebook id,github id etc

    //Add More Fields if needed
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // If user get dleted then all the contacts related to that user will also get deleted
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>(); // For Keeping User roles Dynamic
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //this method is for role
        //we want to pass the user which will have granted authority
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());    //we will traverse roleList using map
        return roles;
        // return Collections.emptyList(); // we said that users don't have any roles
    }

    // Here we don't have getpassword method because we have already declare password @getter generated that
    
    // For this Project:
    // Our Email id is our username   
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

   
}

