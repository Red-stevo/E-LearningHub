package com.redstevo.code.Services;

import com.redstevo.code.CustomExceptions.*;
import com.redstevo.code.Models.AuthRequestModel;
import com.redstevo.code.Models.AuthResponseModel;
import com.redstevo.code.Repositories.AuthRepository;
import com.redstevo.code.Repositories.ProfileRepository;
import com.redstevo.code.Repositories.TokensRepository;
import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.TokensTable;
import com.redstevo.code.Tables.UserProfile;
import freemarker.template.TemplateException;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final AuthRepository authRepository;

    private final ProfileRepository profileRepository;

    private final MailingService mailingService;

    private AuthResponseModel authResponseModel;

    private final TokensRepository tokensRepository;

    private final OTPService otpService;

    private final HttpSession httpSession;
    @PostConstruct
    private void prepare(){
        authResponseModel = new AuthResponseModel();
    }

    public ResponseEntity<AuthResponseModel> register(AuthRequestModel requestModel){
        log.info("Processing the request");

        /*Confirm username availability*/
        if(!Boolean.TRUE.equals(isUsernameAvailable(requestModel.getUsername()).getBody())){
            throw new UsernameNameNotAvailableException("The Username you entered is already in use.");
        }

        /*Confirm email availability*/
        if(!Boolean.TRUE.equals(isEmailAvailable(requestModel.getEmail()).getBody())){
            throw new EmailNotAvailableException("The Email you entered is already in use.");
        }

        sendEmail(requestModel);

        /*Save the user to the database.*/
        AuthTable authTable = new AuthTable();

        /*Setting data for storage*/
        authTable.setUsername(requestModel.getUsername());
        authTable.setPassword(passwordEncoder.encode(requestModel.getPassword()));

        /*Saving the user to the database*/
        authRepository.save(authTable);

        /*Set the account deletion after $* hour if the account is not verified.*/
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //delete the user account.
            }
        }, 1000*60*60);

        /*prepare the profile model.*/
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(requestModel.getUsername());
        userProfile.setEmail(requestModel.getEmail());
        userProfile.setAuthTable(authTable);

        /*Save user profile to the database.*/
        profileRepository.save(userProfile);

        /*Preparing user response.*/
       authResponseModel.setMessage("Check Your Email For A Verification Code.The Code Expires in 5 minutes.");

        log.info("User Created Successfully");
        return ResponseEntity.ok(authResponseModel);
    }

    public void sendEmail(AuthRequestModel requestModel) {
        /*Create a new Thread for Sending the OTP via Email*/

        Thread sendEmail = new Thread(() -> {
            try {
                mailingService.sendVerificationEmail(requestModel.getEmail(), requestModel.getUsername());
            } catch (MessagingException e) {
                throw  new ErrorSendingEmail("Failed To Send The Email.");
            } catch (IOException e) {
                throw new RuntimeException("Error Loading The email.");
            } catch (TemplateException e) {
                throw new InternalError("Error Processing the email.");
            }
        }, "Email Sender Thread.");

        /*Executing the thread*/
        sendEmail.start();
    }

    private String generateToken(AuthTable authTable) {

        /*Saving the user token*/
        TokensTable tokensTable = new TokensTable();
        tokensTable.setAuthTable(authTable);
        tokensTable.setToken(jwtService.generateToken(authTable));

        tokensRepository.save(tokensTable);

        return tokensTable.getToken();
    }

    public ResponseEntity<Boolean> isUsernameAvailable(
            @Size(min = 2, max = 20, message = "Username Must Be Between 2-20 characters.") String username) {
        return new ResponseEntity<>
                (authRepository.countAllByUsername(username).orElse(0) == 0, HttpStatus.OK);
    }

    public ResponseEntity<Boolean> isEmailAvailable(
            @Email String email) {
        log.info("Checking the email");
        return new ResponseEntity<>((profileRepository.countByEmail(email).orElse(0) == 0), HttpStatus.OK);
    }


    public ResponseEntity<AuthResponseModel> verifyOTP(String code) {
        log.info("verifying the user.");

        /*Get User OTP*/
        String otp =  otpService.getOTP();

        /*Check if the code has been removed inferring it has expired.*/
        if(otp == null){
            throw new CodeExpiredException("Your Verification Code Has Already Expired, Click On Resend ans Try Again");
        }

        /*Check if the code entered matches the stored one*/
        if(!code.equals(otp)){
            log.warn("The OTP did not match");
            throw new InvalidOTPException("Incorrect Code");
        }

        /*Generate the user token*/
        String username = String.valueOf(httpSession.getAttribute(httpSession.getId() + "name"));

        if(username == null){
            log.warn("Username removed, code had already expired.");
            throw new CodeExpiredException("Your Verification Code Has Already Expired, Click On Resend ans Try Again");
        }

        AuthTable authTable = authRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username was not Found."));

        /*Check if the email has already been verified*/
        if(authTable.getIsEnabled()){
            log.warn("Email Already Verified");
            return ResponseEntity.ok(null);
        }

        authTable.setIsEnabled(true);

        /*Update it enabled status.*/
        authRepository.save(authTable);


        /*Get the available user profile.*/
        UserProfile userProfile = profileRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Does Not Exist Exception")
        );

        /*Generate ans save email to database.*/
        String jwt = generateToken(authTable);

        /*prepare user response*/
        authResponseModel.setJwt(jwt);
        authResponseModel.setId(authTable.getUserId());
        authResponseModel.setEmail(userProfile.getEmail());
        authResponseModel.setMessage("Registration Successful");

        return new ResponseEntity<>(authResponseModel, HttpStatus.CREATED);
    }
}

