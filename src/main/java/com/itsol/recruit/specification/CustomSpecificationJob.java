package com.itsol.recruit.specification;

import com.itsol.recruit.entity.Job;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SuppressWarnings("serial")
@RequiredArgsConstructor
public class CustomSpecificationJob  implements Specification<Job> {

    @NonNull
    private String field;

    @NonNull
    private Object value;
    @Override
    public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("name")) {
            return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
        }else if (field.equalsIgnoreCase("salaryMax")) {
            return criteriaBuilder.like(root.get("salaryMax"), "%" + value.toString() + "%");
        }else if (field.equalsIgnoreCase("dueDate")) {
            return criteriaBuilder.like(root.get("dueDate"), "%" + value.toString() + "%");
        }
        return null;
    }
}

