package com.redstevo.code.Services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailingService {

    private final JavaMailSender javaMailSender;

    private final Configuration configuration;

    private final OTPService otpService;

    @Async
    public void sendVerificationEmail(String email, String username)
            throws IOException, TemplateException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        /*Create a mail helper*/
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


        /*Generate the otp*/
        otpService.generateOTP(username);


        /*Get the generated otp*/
        String OTP = otpService.getOTP();

        System.out.println(OTP);

        Map<String, String> htmlData = new HashMap<>();
        htmlData.put("otp", OTP);

        /*Load the email template*/
        Template emailTemplate = configuration.getTemplate("email.ftl");
        String emailHTML = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate, htmlData);

        messageHelper.setTo(email);
        messageHelper.setFrom("Student Learning Assistant");
        messageHelper.setText(emailHTML);

        javaMailSender.send(mimeMessage);
    }
}
