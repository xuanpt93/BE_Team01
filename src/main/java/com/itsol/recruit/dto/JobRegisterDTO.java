package com.itsol.recruit.dto;

import com.fasterxml.jackson.databind.JsonSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobRegisterDTO {

    @NonNull
    String addressInterview;
    @NonNull
    String cvFile;
    @NonNull
    Date dateInterview;
    @NonNull
    Date dateRegister;
    @NonNull
    Long jobId;
    @NonNull
    String mediaType;
    @NonNull
    String methodInterview;
    @NonNull
    Long userId;




}
