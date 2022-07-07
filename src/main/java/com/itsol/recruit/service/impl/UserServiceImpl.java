package com.itsol.recruit.service.impl;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.itsol.recruit.repository.OTPRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    OTPRepository otpRepository;

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean isExistedUser(String userName){
        if (userRepository.findByUserName(userName) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistedUserByEmail(String Email) {
        if (userRepository.findByEmail(Email).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void sendConfirmUserRegistrationViaEmail(String email) {
        eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
    }

    @Override
    public void activeAccount(Long id) {
        User user = userRepository.getById(id);
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public String generateOTP(User user) {
        int max = 99999;
        int min = 10000;
        Long otp = (long) ((Math.random() * (max - min)) + min);
        String otpStr = otp + "";
        OTP otp1 = new OTP(user);
        otp1.setCode(otpStr);
        Date issueAt = new Date();
        Long issue = issueAt.getTime() + Constants.OTP.EXPIRED_TIME;
        otp1.setIssueAt(issue);
        otpRepository.save(otp1);
        return otpStr;


    }

    @Override
    public User findByPhonenumber(String phone) {
        return userRepository.findUserByPhoneNumber(phone);
    }



}
