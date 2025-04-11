package vn.edu.stu.backend_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.stu.backend_service.controller.request.PositionRequest;
import vn.edu.stu.backend_service.controller.request.PositionUpdateRequest;
import vn.edu.stu.backend_service.controller.response.position.PositionDetailResponse;
import vn.edu.stu.backend_service.controller.response.position.PositionPageRespone;
import vn.edu.stu.backend_service.controller.response.position.PositionResponse;

import vn.edu.stu.backend_service.exception.InvalidDataException;
import vn.edu.stu.backend_service.exception.ResourceNotFoundException;
import vn.edu.stu.backend_service.mapper.PositionMapper;
import vn.edu.stu.backend_service.model.DepartmentEntity;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.PositionEntity;
import vn.edu.stu.backend_service.repository.PositionRepository;
import vn.edu.stu.backend_service.service.DepartmentService;
import vn.edu.stu.backend_service.service.PositionService;
import vn.edu.stu.backend_service.specification.PositionSpecification;


import java.util.List;
import java.util.Optional;

@Slf4j(topic = "POSITION-SERVICE")
@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService{
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;
    private final DepartmentService departmentService;

    @Transactional
    @Override
    public PositionEntity savePosition(PositionRequest request) {
        log.info("Begin Saving position {}", request);
        Optional<PositionEntity> position = positionRepository.findByName(request.getName());
        if(position.isPresent()) {
            throw new InvalidDataException("Position name already exists");
        }
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setName(request.getName());
        positionEntity.setDescription(request.getDescription());

        if(request.getDepartmentId() != null) {
            DepartmentEntity department = departmentService.getDepartmentById(request.getDepartmentId());
            positionEntity.setDepartment(department);
        }
        positionRepository.save(positionEntity);

        log.info("Saving position {}", request);
        return positionEntity;
    }

    @Override
    public void deletePosition(Long id) {
        PositionEntity position = getPositionById(id);
        if(position != null) {
            if(position.getEmployees().isEmpty()){
                positionRepository.delete(position);
            }else{
                throw new InvalidDataException("Cannot delete position because it contains employee table constraints");
            }
        }
    }

    @Override
    public void updatePosition(PositionUpdateRequest request) {
        log.info("Begin update position {}", request);

        PositionEntity position = getPositionById(request.getId());
        if(position != null) {
            position.setName(request.getName());
            position.setDescription(request.getDescription());

            if(request.getDepartmentId() != null) {
                DepartmentEntity department = departmentService.getDepartmentById(request.getDepartmentId());
                position.setDepartment(department);
            }
            positionRepository.save(position);

            log.info("Updating position {}", request);
        }

    }


    @Override
    public PositionEntity getPositionByName(String name) {
        return positionRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException("Position not found"));
    }

    @Override
    public PositionEntity getPositionById(Long id) {
        return positionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Position not found"));
    }

    @Override
    public PositionPageRespone getAllPositions(String keyword, String sort, int page, int size) {
        Specification<PositionEntity> spec = Specification.where(PositionSpecification.hasKeyword(keyword));

        Sort.Direction direction = ("desc".equalsIgnoreCase(sort)) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortBy = Sort.by(direction, "id");

        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<PositionEntity> positionEntities = positionRepository.findAll(spec, pageable);

        List<PositionResponse> positionResponses = positionMapper.toPositionResponesList(positionEntities.toList());

        PositionPageRespone positionPageRespone = new PositionPageRespone();
        positionPageRespone.setPositions(positionResponses);
        positionPageRespone.setPageNumber(positionEntities.getNumber());
        positionPageRespone.setPageSize(positionEntities.getSize());
        positionPageRespone.setTotalPages(positionEntities.getTotalPages());
        positionPageRespone.setTotalElements(positionEntities.getTotalElements());

        return positionPageRespone;
    }

    @Override
    public List<PositionEntity> getAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public List<EmployeeEntity> getEmployeeByPositionId(Long id) {
        PositionEntity position = getPositionById(id);
        if(position != null) {
            return position.getEmployees();
        }
        return null;
    }

    @Override
    public long totalPositions() {
        return positionRepository.count();
    }

    @Override
    public List<PositionDetailResponse> getAllPositionDetails() {
        return positionRepository.getAllPositionDetails();
    }
}
