package com.itsol.recruit.specification;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@RequiredArgsConstructor
public class CustomJobRegisterSpecification implements Specification<JobRegister> {
    @NonNull
    private String field;

    @NonNull
    private Object value;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobRegisterRepository registerRepository;

    @Override
    public Predicate toPredicate(Root<JobRegister> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        if (field.equalsIgnoreCase("user")) {
//            return criteriaBuilder.like(root.get("user.Username"), "%" + value.toString() + "%");
//        }else if (field.equalsIgnoreCase("dateInterview")) {
//            return criteriaBuilder.like(root.get("dateInterview"), "%" + value.toString() + "%");
//        }else if (field.equalsIgnoreCase("dateRegister")) {
//            return criteriaBuilder.like(root.get("dateRegister"), "%" + value.toString() + "%");
//        }
//        else if (field.equalsIgnoreCase("typeInterview")) {
//            return criteriaBuilder.like(root.get("typeInterview"), "%" + value.toString() + "%");
//        }
//        else {

            Join<JobRegister,User> userJobRegisterJoin = root.join("users");
            return criteriaBuilder.equal(userJobRegisterJoin.get("name"),field);

    }

}
