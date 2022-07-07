package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class UserController {

   public final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getAllUser(){
        return  ResponseEntity.ok().body( userService.getAllUser());
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> findUserById(@RequestParam("id") Long id){
        return  ResponseEntity.ok().body( userService.findById(id));
    }

    @PreAuthorize("permitAll()")
    @PostMapping(value = "active_account")
    public ResponseEntity<?> activeAccount(@RequestParam("id") Long id){
        userService.activeAccount(id);
        return  ResponseEntity.ok().body("ok");
    }
}
