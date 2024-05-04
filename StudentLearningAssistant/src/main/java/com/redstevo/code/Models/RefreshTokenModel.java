package com.redstevo.code.Models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class RefreshTokenModel {

    private String username;

    private Long id;

    private String accessToken;

    private Date date;

}
