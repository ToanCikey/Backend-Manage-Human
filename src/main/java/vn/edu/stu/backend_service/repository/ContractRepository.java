package vn.edu.stu.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.edu.stu.backend_service.model.ContractEntity;
@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long>, JpaSpecificationExecutor<ContractEntity> {

}
