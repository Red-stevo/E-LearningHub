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


    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!`~*/.,<>?_;:&-+=()])(?=\\S+$).{8,20}$",
    message = "The password must contain at least 1 Upper case character, at least 1 lowercase character, a number " +
            "a special character and should have 8 - 20 characters")
    private  String password;

    @Email(message = "You Entered An Invalid Email.")
    private String email;
}
