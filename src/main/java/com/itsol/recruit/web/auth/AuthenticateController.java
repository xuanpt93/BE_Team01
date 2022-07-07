package com.itsol.recruit.web.auth;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.security.jwt.JWTFilter;
import com.itsol.recruit.security.jwt.JWTTokenResponse;
import com.itsol.recruit.security.jwt.TokenProvider;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.web.vm.LoginVM;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public AuthenticateController(AuthenticateService authenticateService, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService, TokenProvider tokenProvider) {
        this.authenticateService = authenticateService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDTO dto) {
        authenticateService.signup(dto);
        return ResponseEntity.ok().body("ok");
    }

    /*
    Login api
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginVM loginVM) {
//		Tạo chuỗi authentication từ username và password (object LoginRequest
//		- file này chỉ là 1 class bình thường, chứa 2 trường username và password)
        UsernamePasswordAuthenticationToken authenticationString = new UsernamePasswordAuthenticationToken(
                loginVM.getUserName(),
                loginVM.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationString);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginVM.getRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
//        return new ResponseEntity<>(Collections.singletonMap("token", jwt), httpHeaders, HttpStatus.OK); //Trả về chuỗi jwt(authentication string)

        User userLogin = userService.findUserByUserName(loginVM.getUserName());
        return ResponseEntity.ok().body(new JWTTokenResponse(jwt, userLogin)); //Trả về chuỗi jwt(authentication string)

    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam("id") Long id, @RequestParam("password") String pass) {
        authenticateService.changePassword(id, pass);
        return ResponseEntity.ok().body("ok");
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/opt-generaing")
    public ResponseEntity<?> sendOtpToEmail(@RequestParam("email") String email) {
        authenticateService.sendOtpToGmail(email);
        return ResponseEntity.ok().body("ok");
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/newPass-setting")
    public ResponseEntity<?> resetPassword(@RequestParam("opt") String optGen, @RequestParam("newpass") String newpass) {
        authenticateService.takeNewPassword(optGen, newpass);
        return ResponseEntity.ok().body("ok");
    }
}
