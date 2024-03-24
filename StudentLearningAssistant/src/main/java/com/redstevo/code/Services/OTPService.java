package com.redstevo.code.Services;

import com.redstevo.code.Repositories.CodeRepository;
import com.redstevo.code.Tables.Code;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
@RequiredArgsConstructor
public class OTPService {

   private final CodeRepository codeRepository;

    private void storeOTP(String otp, String username){
        /*Configuring the otp table.*/
        Code code = new Code();
        code.setUsername(username);
        code.setCode(otp);

        /*Saving the otp*/
        codeRepository.save(code);
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

    public String getOTP(String username){
        return codeRepository
                .findByUsername(username)
                .orElseThrow(() -> {return  new UsernameNotFoundException("Code Expired, " +
                        "Click on resend to get another code");} )
                .getUsername();
    }

    private void removeOTP() {
        /*Run the remove on when an otp has been set.*/

    }

}
