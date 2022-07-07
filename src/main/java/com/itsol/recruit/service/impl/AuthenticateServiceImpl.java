package com.itsol.recruit.service.impl;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.event.IMailService;
import com.itsol.recruit.repository.AuthenticateRepository;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.AuthenticateService;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    UserService userService;

    @Autowired
    IMailService iMailService;

    @Autowired
    JavaMailSender javaMailSender;

    private String otp;
    private User user;

    public final AuthenticateRepository authenticateRepository;

    public final UserMapper userMapper;

    public final RoleRepository roleRepository;

    public final UserRepository userRepository;

    public AuthenticateServiceImpl(AuthenticateRepository authenticateRepository, UserMapper userMapper, RoleRepository roleRepository, UserRepository userRepository) {
        this.authenticateRepository = authenticateRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public User signup(UserDTO dto) {
        try {
            Set<Role> roles = roleRepository.findByCode(Constants.Role.USER);
            User user = userMapper.toEntity(dto);
            user.setDelete(false);
            user.setActive(false);
            user.setActive(false);
            user.setDelete(false);
            user.setRoles(roles);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String enCryptPassword = passwordEncoder.encode(dto.getPassword());
            user.setPassword(enCryptPassword);
            userRepository.save(user);
            iMailService.sendRegistrationUserConfirm(user.getEmail());
            return user;
        } catch (Exception e) {
            log.error("cannot save to database");
            return null;
        }
    }


    public void changePassword(Long id, String newPassword) {
        User user = userService.findById(id);
        if (user != null) {
            if (newPassword.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String enCryptPassword = passwordEncoder.encode(newPassword);
                user.setPassword(enCryptPassword);
                userRepository.save(user);
            }

        }

    }

    public String sendOtpToGmail(String gmail) {
        this.user = userService.findUserByEmail(gmail);
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("hulkhulk1245@gmail.com");
            mimeMessageHelper.setTo(gmail);
            mimeMessageHelper.setText("Please dont share this token with anyone else: " + this.otp);
            this.otp = userService.generateOTP(user) + "";
            mimeMessageHelper.setSubject("Verifing forgot password");
            javaMailSender.send(mimeMessage);
            return this.otp;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeNewPassword(String otpTaken, String newPassword) {
        if (otpTaken.equals(this.otp)) {
            if (newPassword.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String enCryptPassword = passwordEncoder.encode(newPassword);
                this.user.setPassword(enCryptPassword);
                userRepository.save(this.user);
            }

        }

    }

}
