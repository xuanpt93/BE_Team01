package com.itsol.recruit.dto;

import com.itsol.recruit.validation.EmailDuplicateValidation;
import com.itsol.recruit.validation.EmailFormatValidation;
import com.itsol.recruit.validation.PasswordlFormatValidation;
import com.itsol.recruit.validation.UsernameDuplicateValidation;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserDTO {


    String fullName;

    @NotNull
    @EmailFormatValidation
    @EmailDuplicateValidation
    String email;

    @NotNull
    @UsernameDuplicateValidation
    String userName;

    @NotNull
    @PasswordlFormatValidation
    String password;

    String phoneNumber;

    String homeTown;

    String avatarName;

    String gender;

    @NotNull
    @PasswordlFormatValidation
    String newPassword;

    String name;

    Date birthDay;
}
