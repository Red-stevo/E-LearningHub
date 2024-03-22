package com.redstevo.code.Services;

import freemarker.template.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailingService {

    private final MailSender mailSender;

    private final Configuration configuration;


    public void sendVerificationEmail(String email, String username) {
        throw new RuntimeException("I Am Hungry men.");
    }
}
