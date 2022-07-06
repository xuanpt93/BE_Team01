package com.itsol.recruit.service;


import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.User;

public interface AuthenticateService {
    public User signup(UserDTO dto);

    void changePassword(Long id, String newPasswors);

    String sendOtpToGmail(String gmail);

    void takeNewPassword(String otpTaken, String newPassword);
}
