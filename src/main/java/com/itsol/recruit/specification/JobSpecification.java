package com.itsol.recruit.specification;
import com.itsol.recruit.entity.Job;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class JobSpecification {

    public static Specification<Job> buildWhere(String search){
        Specification<Job> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecificationJob name = new CustomSpecificationJob("name", search);
            CustomSpecificationJob salaryMax = new CustomSpecificationJob("salaryMax", search);
            CustomSpecificationJob dueDate = new CustomSpecificationJob("dueDate", search);

            where = Specification.where(name).or(salaryMax).or(dueDate);
        }

        return where;
    }
}
