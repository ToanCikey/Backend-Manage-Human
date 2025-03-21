package vn.edu.stu.backend_service.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.edu.stu.backend_service.model.PositionEntity;

public class PositionSpecification {
    public static Specification<PositionEntity> hasKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isEmpty()) {
                return null;
            }
            String likePattern = "%" + keyword + "%";
            return criteriaBuilder.like(root.get("name"), likePattern
            );
        });
    }
}
