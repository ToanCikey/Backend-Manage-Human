package vn.edu.stu.backend_service.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.stu.backend_service.model.UserEntity;

public class UserSpecification {
    public static Specification<UserEntity> hasKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isEmpty()) {
                return null;
            }
            String likePattern = "%" + keyword + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("username"), likePattern),
                    criteriaBuilder.like(root.get("email"), likePattern),
                    criteriaBuilder.like(root.get("status"), likePattern),
                    criteriaBuilder.like(root.get("type"), likePattern)
            );
        });
    }
}
