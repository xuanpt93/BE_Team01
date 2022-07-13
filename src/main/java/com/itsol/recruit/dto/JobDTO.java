package com.itsol.recruit.dto;

import com.itsol.recruit.entity.*;
import javafx.print.PrinterJob;
import lombok.*;
import lombok.experimental.FieldDefaults;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobDTO {

    @NonNull
    Long id;

    @NonNull
    @Size(max = 150)
    String name;

    @NonNull
    JobPosition jobPosition;

    @NonNull
    Integer numberExperience;

    @NonNull
    WorkingForm workingForm;

    @NonNull
    @Size(max = 300)
    String addressWork;

    @NonNull
    AcademicLevel academicLevel;

    @NonNull
    Rank rank;

    @NonNull
    Integer qtyPerson;

    @NonNull
    Date startRecruitmentDate;

    @NonNull
    Date dueDate;

    String skills;

    @Size(max = 2000)
    String description;

    String benefits;

    String jobRequirement;

    @NonNull
    Integer salaryMax;

    @NonNull
    Integer salaryMin;

    @NonNull
    User contact;

    User creater;

    Date createDate;

    @NonNull
    User updateUser;

    @NonNull
    Date updateDate;

    @NonNull
    StatusJob statusJob;

    @NonNull
    Integer views;
}
