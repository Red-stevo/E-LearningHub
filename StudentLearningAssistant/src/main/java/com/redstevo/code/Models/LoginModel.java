package com.redstevo.code.Models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {

    @NotNull(message = "Login Username Can Not Be Null")
    private String username;

    @NotNull(message = "Login Password Can Not Be Null")
    private String password;
}
