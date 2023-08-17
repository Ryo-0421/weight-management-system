package com.example.weight_management_system.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SignupForm {
    private Integer roleCode;
    @NotBlank
    @Length(min = 1, max = 10)
    private String name;
    @NotBlank
    @Length(min = 4, max = 10)
    @Pattern(regexp = "^[0-9a-zA-Z]+$")
    private String password;
    @NotBlank
    @Email
    private String email;
}
