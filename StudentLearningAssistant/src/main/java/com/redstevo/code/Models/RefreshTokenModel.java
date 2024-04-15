package com.redstevo.code.Models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class RefreshTokenModel {

    private String accessToken;

    private Date date;

}
