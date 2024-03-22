package com.redstevo.code.Services;

import com.redstevo.code.CustomExceptions.EmailNotAvailableException;
import com.redstevo.code.CustomExceptions.ErrorSendingEmail;
import com.redstevo.code.CustomExceptions.UsernameNameNotAvailableException;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.util.InMemoryResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;

    private final AuthRepository authRepository;

    private final ProfileRepository profileRepository;

    private final MailingService mailingService;

    private AuthResponseModel authResponseModel;

    private final TokensRepository tokensRepository;
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

    private void generateToken(AuthTable authTable) {
        /*Saving the user token*/
        TokensTable tokensTable = new TokensTable();
        tokensTable.setAuthTable(authTable);
        tokensTable.setToken(authResponseModel.getJwt());

        tokensRepository.save(tokensTable);
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


}

