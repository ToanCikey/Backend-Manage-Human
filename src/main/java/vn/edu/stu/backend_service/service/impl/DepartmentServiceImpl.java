package vn.edu.stu.backend_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.controller.request.DepartmentRequest;
import vn.edu.stu.backend_service.controller.request.DepartmentUpdate;
import vn.edu.stu.backend_service.controller.response.department.DepartmentPageResponse;
import vn.edu.stu.backend_service.controller.response.department.DepartmentResponse;
import vn.edu.stu.backend_service.exception.InvalidDataException;
import vn.edu.stu.backend_service.exception.ResourceNotFoundException;
import vn.edu.stu.backend_service.mapper.DepartmentMapper;
import vn.edu.stu.backend_service.model.DepartmentEntity;
import vn.edu.stu.backend_service.repository.DepartmentRepository;
import vn.edu.stu.backend_service.service.DepartmentService;
import vn.edu.stu.backend_service.specification.DepartmentSpecification;

import java.util.List;
import java.util.Optional;

@Slf4j(topic = "DEPARTMENT-SERVICE")
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentEntity addDepartment(DepartmentRequest department) {
        log.info("Begin Saving department {}", department);

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findByName(department.getName());
        if (departmentEntity.isPresent()) {
            throw new ResourceNotFoundException("Department with" + department.getName() + " already exists");
        }

        DepartmentEntity newDepartment = new DepartmentEntity();
        newDepartment.setName(department.getName());
        newDepartment.setDescription(department.getDescription());
        departmentRepository.save(newDepartment);

        log.info("Saved department {}", department);
        return newDepartment;
    }

    @Override
    public void updateDepartment(DepartmentUpdate department) {
        log.info("Begin Updating department {}", department);

        DepartmentEntity departmentEntity = getDepartmentById(department.getId());
        if (departmentEntity != null) {
            departmentEntity.setName(department.getName());
            departmentEntity.setDescription(department.getDescription());
            departmentRepository.save(departmentEntity);
        }

        log.info("Updating department {}", department);
    }

    @Override
    public void deleteDepartment(Long id) {
        log.info("Begin Deleting department {}", id);

        DepartmentEntity departmentEntity = getDepartmentById(id);
        if (departmentEntity != null) {
            if(departmentEntity.getEmployees().isEmpty() && departmentEntity.getPositions().isEmpty()) {
                departmentRepository.delete(departmentEntity);
            }else{
                throw new InvalidDataException("Cannot delete department because it contains employee and position table constraints");
            }
        }

        log.info("Deleting department {}", id);
    }

    @Override
    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department with id = " + id +" not found"));
    }

    @Override
    public DepartmentEntity getDepartmentByName(String name) {
        return departmentRepository.findByName(name).orElseThrow(() -> new RuntimeException("Department with name = " + name +" not found"));
    }

    @Override
    public DepartmentPageResponse getAllDepartments(String keyword, String sort, int page, int size) {
        Specification<DepartmentEntity> specification = DepartmentSpecification.hasKeyword(keyword);

        Sort.Direction direction = ("desc".equalsIgnoreCase(sort)) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortBy = Sort.by(direction, "id");

        Pageable pageable = PageRequest.of(page, size, sortBy);

        Page<DepartmentEntity> departmentEntities = departmentRepository.findAll(specification, pageable);

        List<DepartmentResponse> departmentResponses = departmentMapper.toMapListDepartment(departmentEntities.getContent());

        DepartmentPageResponse departmentPageResponse = new DepartmentPageResponse();
        departmentPageResponse.setDepartments(departmentResponses);
        departmentPageResponse.setPageNumber(departmentEntities.getNumber());
        departmentPageResponse.setPageSize(departmentEntities.getSize());
        departmentPageResponse.setTotalPages(departmentEntities.getTotalPages());
        departmentPageResponse.setTotalElements(departmentEntities.getTotalElements());

        return departmentPageResponse;
    }

    @Override
    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
