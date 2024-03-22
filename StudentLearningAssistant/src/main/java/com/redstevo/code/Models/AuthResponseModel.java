package com.redstevo.code.Models;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AuthResponseModel {

    private String username;

    private String message;

    private String email;

    private String jwt;

    private Long id;

    public AuthResponseModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public AuthResponseModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public AuthResponseModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public AuthResponseModel setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public AuthResponseModel setId(Long id) {
        this.id = id;
        return this;
    }

    public void build(){

    }
}
