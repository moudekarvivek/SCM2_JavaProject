package com.scmvivek.forms;

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
    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;
}
