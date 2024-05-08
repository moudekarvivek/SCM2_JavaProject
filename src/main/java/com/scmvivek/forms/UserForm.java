package com.scmvivek.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
// WE Will make the variables which we will recieve from the form
    @NotBlank(message = "username is required")
    @Size(message = "Min 3 Characters are required", min = 3)
    private String name;

    @Email(message = "Invalid Email Address") // annotation for Validation of Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(message = "Min 6 character are required", min = 6)
    private String password;
    
    @NotBlank(message = "About is required")
    private String about;
    
    @Size(message = "Invalid Phone Number", min = 8, max = 12)
    private String phoneNumber;
}
