package com.itsol.recruit.specification;

import com.itsol.recruit.entity.JobRegister;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class JobRegisterSpecification {
    public static Specification<JobRegister> buildWhere(String search){
        Specification<JobRegister> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
//            CustomJobRegisterSpecification name = new CustomJobRegisterSpecification("name", search);
            CustomJobRegisterSpecification name = new CustomJobRegisterSpecification("users.userName", search);
            CustomJobRegisterSpecification dateInterview  = new CustomJobRegisterSpecification("dateInterview", search);
            CustomJobRegisterSpecification dateRegister = new CustomJobRegisterSpecification("dateRegister", search);
            CustomJobRegisterSpecification typeInterview = new CustomJobRegisterSpecification("typeInterview", search);

            where = Specification.where(name).or(dateInterview).or(dateRegister).or(typeInterview);
        }

        return where;
    }
}
