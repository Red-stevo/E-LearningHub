package com.redstevo.code.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthRequestModel {

    @Size(max = 50, min = 2, message = "Username Must Be Between 2 - 50 characters.")
    private String username;

    @Pattern(regexp = "^(?=.*\\p{Upper})(?=.*\\p{Lower})(?=.*\\p{N})(?=.*\\p{S})$" ,
    message = "The password must contain at least 1 Upper case character, at least ! lowercase character, a number " +
            "and a special character")
    @Size(min = 8, max = 20, message = "Password must contain 8 - 20 Alphanumerics")
    private  String password;

    @Email(message = "You Entered An Invalid Email.")
    private String email;
}
