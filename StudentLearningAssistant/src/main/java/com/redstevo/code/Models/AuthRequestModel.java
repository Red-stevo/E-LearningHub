package com.redstevo.code.Models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthRequestModel {

    @NotNull
    private String username;

    @Pattern(regexp = "^?=.*\\p{Upper}?=.*\\p{Lower}?=.*\\p{N}?=.*\\p{S}$" ,
    message = "The password must contain at least 1 Upper case character, at least ! lowercase character, a number " +
            "and a special character")
    @Size(min = 8, max = 20, message = "Password must contain 8 - 20 Alphanumerics")
    private  String password;

    @NotNull
    private String Email;
}
