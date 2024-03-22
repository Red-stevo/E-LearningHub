package com.redstevo.code.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseModel {

    private String secondName;

    private String firstName;

    private String username;

    private String message;

    private String email;

    private byte[] image;

    private String jwt;

    private Long id;

}
