package com.redstevo.code.Controllers;

import com.redstevo.code.Models.*;
import com.redstevo.code.Services.AuthService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    /*
    *This end Point Allows us to create a new user
    * Is takes in a model AuthRequestModel i.e., username, password, and email.
    * The request us forwarded to the service where: -
    * 1. The Password Strength is checked.
    * 2. An OTP is sent to the user to verify their email.
    * 3. Save The User to database
    * */

    @PostMapping("/register")
    public ResponseEntity<GeneralResponseModel> register(
            @Validated @RequestBody AuthRequestModel authRequestModel){
        log.info("Request to register a new user.");
        return authService.register(authRequestModel);
    }


    /*
    * This End Point is used to check where the username,
    * that the user is entering is: -
    * 1. Of the Required size.
    * 2. If it is Already in use.
    * */

    @GetMapping("/check/username")
    public ResponseEntity<Boolean> isUsernameAvailable(
             @RequestParam ("username") String username){
        return authService.isUsernameAvailable(username);
    }



    /*
    * This End Point check the following,
    * 1. Is Email of the correct format i.e., contains @gmail.com.
    * 2. Is Email in use?.
    * */

    @GetMapping("/check/email")
    public ResponseEntity<Boolean> isEmailAvailable(@RequestParam ("email")String email){
        return authService.isEmailAvailable(email);
    }

    /*
    * 1.Verify otp
    * 2. Generate a jwt
    * 3. The Jwt is saved to the token table.
    * 4. User Response is gotten from the database.
    * 5. Response is give back
    **/
    @PutMapping("/verify/code")
    public ResponseEntity<AuthResponseModel> verifyOTP(
            @RequestParam ("code")String code,
            @RequestParam ("username")String username){

        log.info("Verify user OTP");

        return authService.verifyOTP(code, username);
    }


    /*
    * The End Point Allow User to
    *  1. Send Request To resend an email if they never got the code.
    *  2. Give a response on what why should do next.
    * */
    @PutMapping("/verify/resend")
    public ResponseEntity<GeneralResponseModel> verifyResend(String username){
        log.info("Resend Code.");
        return authService.resendEmail(username);
    }


    /*
    * This end point will handle user login,
    * checks the password and username passed and validates that they are present and correct*/

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> login(
            @Validated @RequestBody LoginModel loginModel){
        log.info("Login Request");

        return  authService.userLogin(loginModel);
    }



    /*
    * This end point will handle the refreshing of the access token.
    * 1. Validates the refresh token passed in the request cookie
    * 2. generate new access and refresh tokens
    * 3. set the token to the user response*/

    @PutMapping("/token/refresh")
    public  ResponseEntity<RefreshTokenModel> refreshToken(){
        log.info("refresh token request");
        return authService.TokenRefresh();
    }
}
