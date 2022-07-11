package com.itsol.recruit.web.auth;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.OTPRepository;
import com.itsol.recruit.security.jwt.JWTFilter;
import com.itsol.recruit.security.jwt.JWTTokenResponse;
import com.itsol.recruit.security.jwt.TokenProvider;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.web.vm.LoginVM;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Constants.Api.Path.AUTH)
@Api(tags = "Auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    @Autowired
    OTPRepository otpRepository;

    public AuthenticateController(AuthenticateService authenticateService, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService, TokenProvider tokenProvider) {
        this.authenticateService = authenticateService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDTO dto) {

        if (userService.findUserByUserName(dto.getUserName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Username existed", ""));
        } else if (userService.findUserByEmail(dto.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Email existed", ""));
        }else if (userService.findByPhonenumber(dto.getPhoneNumber()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Phone existed", ""));
        }

            return ResponseEntity.ok().body(authenticateService.signup(dto));
    }

    /*
    Login api
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginVM loginVM) {
        try {
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (userService.findUserByUserName(loginVM.getUserName()) == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new ResponseObject(HttpStatus.BAD_REQUEST, "Incorrect username", ""));
                } else if (!passwordEncoder.matches(loginVM.getPassword(), userService.findUserByUserName(loginVM.getUserName()).getPassword())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new ResponseObject(HttpStatus.BAD_REQUEST, "Incorrect password", ""));
                }
            } catch (Exception e) {

            }
            UsernamePasswordAuthenticationToken authenticationString = new UsernamePasswordAuthenticationToken(
                    loginVM.getUserName(),
                    loginVM.getPassword()
            );
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationString);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, loginVM.getRememberMe());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
            User userLogin = userService.findUserByUserName(loginVM.getUserName());
            return ResponseEntity.ok().body(new JWTTokenResponse(jwt, userLogin)); //Trả về chuỗi jwt(authentication string)
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, e.getMessage(), ""));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam("id") Long id, @RequestParam("password") String pass) {
        authenticateService.changePassword(id, pass);
        return ResponseEntity.ok().body("ok");
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/opt-generaing")
    public ResponseEntity<?> sendOtpToEmail(@RequestParam("email") String email) {
        if (userService.findUserByEmail(email) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Email không tồn tại", ""));
        }
        return ResponseEntity.ok().body(authenticateService.sendOtpToGmail(email));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/newPass-setting")
    public ResponseEntity<?> resetPassword(@RequestParam("opt") String optGen, @RequestParam("newpass") String newpass) {
        if ( otpRepository.findByCode(optGen) == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Otp không đúng hoặc hết hạn!", ""));
        }
        authenticateService.takeNewPassword(optGen, newpass);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
