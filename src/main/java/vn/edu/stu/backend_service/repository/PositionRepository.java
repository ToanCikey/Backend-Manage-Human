package vn.edu.stu.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.stu.backend_service.controller.response.position.PositionDetailResponse;
import vn.edu.stu.backend_service.model.PositionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long>, JpaSpecificationExecutor<PositionEntity> {
    Optional<PositionEntity> findByName(String name);

    @Query("select new vn.edu.stu.backend_service.controller.response.position.PositionDetailResponse(" +
            "p.name, COUNT(DISTINCT e.id))" +
            " from PositionEntity p" +
            " left join p.employees e " +
            "group by p.id, p.name")
    List<PositionDetailResponse> getAllPositionDetails();
}
