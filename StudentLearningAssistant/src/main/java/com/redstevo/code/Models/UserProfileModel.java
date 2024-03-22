package com.redstevo.code.Models;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@Component
public class UserProfileModel {

    private String firstName;

    private String secondName;

    private MultipartFile image;
}
