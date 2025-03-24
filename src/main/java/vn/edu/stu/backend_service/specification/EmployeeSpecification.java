package vn.edu.stu.backend_service.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.stu.backend_service.model.EmployeeEntity;


public class EmployeeSpecification {
    public static Specification<EmployeeEntity> hasKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isEmpty()) {
                return null;
            }
            String likePattern = "%" + keyword + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("fullName"), likePattern),
                    criteriaBuilder.like(root.get("gender"), likePattern),
                    criteriaBuilder.like(root.get("phone"), likePattern),
                    criteriaBuilder.like(root.get("address"), likePattern),
                    criteriaBuilder.like(root.get("employeeStatus"), likePattern)
            );
        });
    }
}
