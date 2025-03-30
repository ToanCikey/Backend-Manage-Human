package vn.edu.stu.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.edu.stu.backend_service.model.SalaryEntity;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, Long>, JpaSpecificationExecutor<SalaryEntity> {

}
