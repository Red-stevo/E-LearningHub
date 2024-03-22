package com.redstevo.code.Models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserProfileModel {

    private String firstName;

    private String secondName;

    private String image;
}
