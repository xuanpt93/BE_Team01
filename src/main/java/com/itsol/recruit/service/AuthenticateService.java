package com.itsol.recruit.service;


import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.security.jwt.JWTTokenResponse;
import com.itsol.recruit.web.vm.LoginVM;

import javax.mail.MessagingException;

public interface AuthenticateService {
    public User signup(UserDTO dto);

    void changePassword(Long id, String newPasswors);

    String sendOtpToGmail(String gmail);

    void takeNewPassword(String otpTaken, String newPassword);
    JWTTokenResponse login(LoginVM loginVM);
}
