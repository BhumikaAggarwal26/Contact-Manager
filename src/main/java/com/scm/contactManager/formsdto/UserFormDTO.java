package com.scm.contactManager.formsdto;

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
public class UserFormDTO {

    @NotBlank(message = "Username is required")
    @Size(min=3, message = "Min 3 characters required")
    private String name;

    @Email(message = "Invalid Email address")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min=6, message = "Min 6 characters required")
    private String password;
    
    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;
    
    private String about;
}
