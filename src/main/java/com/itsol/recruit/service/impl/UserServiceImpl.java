
package com.itsol.recruit.service.impl;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.OTP;
import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.itsol.recruit.repository.OTPRepository;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import com.itsol.recruit.service.mapper.UserMapper;
import com.itsol.recruit.specification.UserSpecification;
import com.itsol.recruit.web.vm.PageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    OTPRepository otpRepository;

    @Autowired
    UserMapper userMapper;
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

    @Override
    public Page<UserDTO> getAllUsers(PageVM pageVM, String search, String sortBy) {
        Pageable firstPageWithTwoElements;
        if(sortBy == null){
             firstPageWithTwoElements = PageRequest.of(pageVM.getPageNumber(), pageVM.getPageSize());
        }else {
             firstPageWithTwoElements = PageRequest.of(pageVM.getPageNumber(), pageVM.getPageSize(), Sort.by(sortBy));
        }
        Specification<User> where = UserSpecification.buildWhere(search);
        return  userRepository.findAll(where, firstPageWithTwoElements).map(userMapper::toDto);
    }

    @Override
    public void deActiveUser(String username) {
        User user = userRepository.findByUserName(username);
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public void createNewUser(UserDTO dto) {
            Set<Role> roles = roleRepository.findByCode(Constants.Role.JE);
            User user = userMapper.toEntity(dto);
            user.setDelete(false);
            user.setActive(true);
            user.setRoles(roles);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String enCryptPassword = passwordEncoder.encode(dto.getPassword());
            user.setPassword(enCryptPassword);
            userRepository.save(user);
    }

    @Override
    public void updateUser(String username, UserDTO dto) {
        Set<Role> roles = roleRepository.findByCode(Constants.Role.JE);
        User user = userMapper.toEntity(dto);
        user.setDelete(false);
        user.setActive(true);
        user.setRoles(roles);
        user.setId(userRepository.findByUserName(username).getId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String enCryptPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword(enCryptPassword);
        userRepository.save(user);
    }

}

