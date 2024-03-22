package com.redstevo.code.Controllers;

import com.redstevo.code.Models.AuthRequestModel;
import com.redstevo.code.Models.AuthResponseModel;
import com.redstevo.code.Services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    /*
    *This end Point Allows us to create a new user
    * Is takes in a model AuthRequestModel i.e., username, password, and email.
    * The request us forwarded to the service where: -
    * 1. The Password Strength is checked.
    * 2. An OTP is sent to the user to verify their email.
    * 3. Save The User to database
    * 4. Generate a jwt
    * 5. The Jwt is saved to the token table.
    * 6. User Response is gotten from the database.
    * 7. Response is give back
    * */

    @PostMapping("/register")
    public ResponseEntity<AuthResponseModel> register(@RequestBody AuthRequestModel authRequestModel){
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
    public ResponseEntity<Boolean> isUsernameAvailable(@RequestParam ("username") String username){

        return authService.isUsernameAvailable(username);
    }



    /*
    * This End Point check the following,
    * 1. Is Email of the correct format i.e., contains @gmail.com.
    * 2. Is Email in use?.
    * */

    @GetMapping("/check/email")
    public ResponseEntity<Boolean> isEmailAvailable(@RequestParam ("email")String email){

        return null;
    }
}
