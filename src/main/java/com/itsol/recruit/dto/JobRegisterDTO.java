package com.itsol.recruit.dto;

import com.fasterxml.jackson.databind.JsonSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobRegisterDTO {

    @NotNull
    Long id;
    @NotNull
    String addressInterview;
    @NotNull
    String cvFile;
    @NotNull
    Date dateInterview;
    @NotNull
    Date dateRegister;
    @NotNull
    Long jobRegisterId;
    @NotNull
    String mediaType;
    @NotNull
    String methodInterview;
    @NotNull
    Long userId;
    @NotNull
    Long statusJobRegisterId;







}
