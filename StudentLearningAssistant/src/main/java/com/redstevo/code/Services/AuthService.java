package com.redstevo.code.Services;

import com.redstevo.code.CustomExceptions.*;
import com.redstevo.code.Models.*;
import com.redstevo.code.Repositories.AuthRepository;
import com.redstevo.code.Repositories.ProfileRepository;
import com.redstevo.code.Repositories.TokensRepository;
import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.TokensTable;
import com.redstevo.code.Tables.UserProfile;
import com.sun.jdi.InternalException;
import freemarker.template.TemplateException;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

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
    private GeneralResponseModel generalResponseModel;

    private final RefreshTokenService refreshTokenService;

    private final HttpServletResponse response;

    /*
    *Method to initialize frequently needed bean to enhance performance.
    * */
    @PostConstruct
    private void prepare(){
        authResponseModel = new AuthResponseModel();
        generalResponseModel = new GeneralResponseModel();
    }

    /*
    * Method to handle user registration.
    * */

    public ResponseEntity<GeneralResponseModel> register(
            @Validated AuthRequestModel requestModel){
        log.info("Processing the request");

        /*Confirm username availability*/
        if(!Boolean.TRUE.equals(isUsernameAvailable(requestModel.getUsername()).getBody())){
            throw new UsernameNotAvailableException("The Username you entered is already in use.");
        }

        /*Confirm email availability*/
        if(!Boolean.TRUE.equals(isEmailAvailable(requestModel.getEmail()).getBody())){
            throw new EmailNotAvailableException("The Email you entered is already in use.");
        }

        /*Sending the email.*/
        try {
            mailingService.sendVerificationEmail(requestModel.getUsername(),requestModel.getEmail());
        } catch (MessagingException e) {
            throw  new ErrorSendingEmail("Failed To Send The Email.");
        } catch (IOException e) {
            throw new InternalException("Error Loading The email.");
        } catch (TemplateException e) {
            throw new InternalError("Error Processing the email.");
        }

        /*Save the user to the database.*/
        AuthTable authTable = new AuthTable();

        /*Setting data for storage*/
        authTable.setUsername(requestModel.getUsername());
        authTable.setPassword(passwordEncoder.encode(requestModel.getPassword()));

        /*Saving the user to the database*/
        authRepository.save(authTable);

        /*prepare the profile model.*/
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(requestModel.getUsername());
        userProfile.setEmail(requestModel.getEmail());
        userProfile.setAuthTable(authTable);


        /*Save user profile to the database.*/
        profileRepository.save(userProfile);

        /*Preparing user response.*/
       generalResponseModel.setMessage("Check Your Email For A Verification Code.");
       generalResponseModel.setDate(new Date());

        log.info("User Created Successfully");
        return ResponseEntity.ok(generalResponseModel);
    }


    /*
    *Method to generate a jwt, save it to the database and return in the verifying or the login controllers.
    * */
    private String generateToken(AuthTable authTable) {

        /*Saving the user token*/
        TokensTable tokensTable = new TokensTable();
        tokensTable.setAuthTable(authTable);
        tokensTable.setToken(jwtService.generateToken(authTable));

        List<TokensTable> tokensTableList = tokensRepository.findByAuthTable(authTable).orElse(null);

        /*Ensure Only one token is set as not logout for a user.*/
        if (tokensTableList != null){
            tokensTableList.forEach((token) -> {
                token.setIsLoggedOut(true);
            });

            tokensRepository.saveAll(tokensTableList);
        }

        tokensRepository.save(tokensTable);

        return tokensTable.getToken();
    }


    /*
    * Method to check whether a username is already used by another user.
    * */
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


    /*
    * Method to verify OTP enter by the user.
    * */
    public ResponseEntity<AuthResponseModel> verifyOTP(String code,String username) {
        log.info("verifying the user.");

        /*Get User OTP*/
        String otp =  otpService.getOTP(username);

        System.out.println(otp);
        /*Check if the code entered matches the stored one*/
        if(!code.equals(otp)){
            log.warn("The OTP did not match");
            throw new InvalidOTPException("Incorrect Code, Please Re-enter The Code.");
        }
        else {
            otpService.removeOTP(username);
        }


        /*Getting user data from the database.*/
        AuthTable authTable = authRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username was not Found."));

        /*Check if the email has already been verified*/
        if(authTable.getIsEnabled()){
            log.warn("Email Already Verified");
            return ResponseEntity.ok(null);
        }

        /*Update the enabled status*/
        authTable.setIsEnabled(true);

        /*Update the enabled status.*/
        authRepository.save(authTable);


        /*Get the available user profile.*/
        UserProfile userProfile = profileRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Does Not Exist Exception")
        );

        /*Generate and save email to database.*/
        String jwt = generateToken(authTable);

        /*prepare user response*/
        authResponseModel.setJwt(jwt);
        authResponseModel.setId(authTable.getUserId());
        authResponseModel.setEmail(userProfile.getEmail());
        authResponseModel.setUsername(userProfile.getUsername());
        authResponseModel.setMessage("Registration Successful");


        /*add Http only cookie to the user response*/
        response.addCookie(generateCookie(authTable));

        return new ResponseEntity<>(authResponseModel, HttpStatus.CREATED);
    }


    /*
    * Method to handle email resending.
    * */
    public ResponseEntity<GeneralResponseModel> resendEmail(String username){

        /*Getting the user with the given provided username from the database*/
        AuthTable authTable = authRepository.findByUsername(username).orElseThrow(
                () -> new UserDoesNotExistException("Could Not Find User, Please Re-enter Your Username.")
        );

        /*Check if user is verified */
        if(authTable.isEnabled()){
            return ResponseEntity.ok(null);
        }

        /*Handling the email resend.*/
        try {
            mailingService.sendVerificationEmail(
                    authTable.getUsername(),
                    profileRepository.findByUsername(username).orElseThrow(() ->
                                    new UsernameNotFoundException
                                            ("Could Not Find User, Please Re-enter Your Username."))
                    .getEmail());


        } catch (MessagingException e) {
            throw  new ErrorSendingEmail("Failed To Send The Email.");
        } catch (IOException e) {
            throw new InternalError("Error Loading The email.");
        } catch (TemplateException e) {
            throw new InternalError("Error Processing the email.");
        }

        /*Preparing user response.*/
        generalResponseModel.setMessage("Check Your Email For A Verification Code.");
        generalResponseModel.setDate(new Date());

        return ResponseEntity.ok(generalResponseModel);
    }

    public ResponseEntity<AuthResponseModel> userLogin(LoginModel loginModel) {
        log.info("Processing user login request");

        /*Pass the user credentials to the auth manager to perform the password and user validation*/
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginModel.getUsername(),
                loginModel.getPassword()
        ));


        /*getting user credentials from the database*/
        AuthTable authTable = authRepository
                .findByUsername(loginModel.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        /*Getting user profile.*/
        UserProfile userProfile = profileRepository
                .findByUsername(loginModel
                        .getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        /*Generate jwt for the user*/
        String jwt = generateToken(authTable);

        /*preparing user response.*/

        /*Getting the user profile image from the file system*/
        if(userProfile.getImageUrl() != null) {
            Path imagePath = Paths.get(userProfile.getImageUrl());
            try {
                authResponseModel.setImage(Files.readAllBytes(imagePath));
            } catch (IOException e) {
                throw new ImageNotFoundException("Profile Image Not Found");
            }
        }

        authResponseModel.setJwt(jwt);
        authResponseModel.setFirstName(userProfile.getFirstName());
        authResponseModel.setMessage("Login Successful");
        authResponseModel.setEmail(userProfile.getEmail());
        authResponseModel.setId(authTable.getUserId());
        authResponseModel.setSecondName(userProfile.getSecondName());
        authResponseModel.setUsername(userProfile.getUsername());


        /*Add cookie to the user response*/
        response.addCookie(generateCookie(authTable));

        return new ResponseEntity<>(authResponseModel, HttpStatus.OK);
    }


    /*
    * This Method is responsible for refresh the Access token and the refresh token.
    * For this the method performs the following functionalities :
    *   1. Get the refreshToken from the cookie passed in the HttpServletRequest
    *   2. Validates that the RefreshToken exist and has not expired
    *   3. Generate an RefreshToken cookie
    *   4. Generate a new accessToken
    *   5. Set the cookie to the HttpServletResponse
    *   6. Return the new generated access token to the client*/

    public ResponseEntity<RefreshTokenModel> TokenRefresh() {
        log.info("Token Refreshing Task");

        /*first two operations*/
        AuthTable authTable = refreshTokenService.checkRefreshTokenValidity();

        //generate new refreshToken and set the cookie to the HttpServletResponse
        response.addCookie(generateCookie(authTable));

        //generate the accessToken and set it to the client response.
        RefreshTokenModel refreshTokenModel = new RefreshTokenModel();
        refreshTokenModel.setAccessToken(jwtService.generateToken(authTable));

        //user response.
        return ResponseEntity.ok(refreshTokenModel);
    }



    /*
    * This method generate a new HTTP Only cookie
    * the method is user after code verification, login and during accessToken refreshing*/
    private Cookie generateCookie(AuthTable authTable){
        //generate a new uuid (RefreshToken)
        String refreshToken = refreshTokenService.generateRefreshToken(authTable);

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24*14);

        return cookie;
    }
}
