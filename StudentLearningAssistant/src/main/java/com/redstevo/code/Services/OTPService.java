package com.redstevo.code.Services;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
@RequiredArgsConstructor
public class OTPService {

    private final HttpSession httpSession;

    private void storeOTP(String otp, String username){
        /*Name Used Get A Specific OTP*/
        String reference = "OTP-"+httpSession.getId();

        String nameRef = httpSession.getId()+"name";
        /*Store the otp to the session storage*/
        httpSession.setAttribute(reference, otp);
        httpSession.setAttribute(nameRef, username);
    }

    public void generateOTP(String username){
        Timer timer = new Timer();
        /*Generating a 6 number otp*/
        String OTP = new DecimalFormat("000000")
                .format(new Random().nextInt(999999));

        /*Saving the number to server storage.*/
        storeOTP(OTP, username);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removeOTP();
            }
        }, 1000*60*6);

    }

    public String getOTP(){
        return String.valueOf(httpSession.getAttribute("OTP-"+httpSession.getId()));
    }


    private void removeOTP() {
        /*Run the remove on when an otp has been set.*/
        if(getOTP() != null) {
            httpSession.removeAttribute("OTP-" + httpSession.getId());
            httpSession.removeAttribute(httpSession.getId()+"name");
        }
    }

}
