package vn.edu.stu.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.stu.backend_service.controller.response.department.DepartmentDetailResponse;
import vn.edu.stu.backend_service.model.DepartmentEntity;
import vn.edu.stu.backend_service.model.EmployeeEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>, JpaSpecificationExecutor<DepartmentEntity> {
    Optional<DepartmentEntity> findByName(String name);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.position.department.id = :id")
    List<EmployeeEntity> getAllEmployeeByDepartmentId(Long id);

    @Query("SELECT new vn.edu.stu.backend_service.controller.response.department.DepartmentDetailResponse(" +
            "d.name, COUNT(DISTINCT p.id), COUNT(DISTINCT e.id)) " +
            "FROM DepartmentEntity d " +
            "LEFT JOIN d.positions p " +
            "LEFT JOIN p.employees e " +
            "GROUP BY d.id, d.name")
    List<DepartmentDetailResponse> getDepartmentDetails();
}
