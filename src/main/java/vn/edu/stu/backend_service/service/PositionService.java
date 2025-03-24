package vn.edu.stu.backend_service.service;


import vn.edu.stu.backend_service.controller.request.PositionRequest;
import vn.edu.stu.backend_service.controller.request.PositionUpdateRequest;
import vn.edu.stu.backend_service.controller.response.position.PositionPageRespone;
import vn.edu.stu.backend_service.model.PositionEntity;



public interface PositionService {
    PositionEntity savePosition(PositionRequest request);
    void deletePosition(Long id);
    void updatePosition(PositionUpdateRequest request);
    PositionEntity getPositionByName(String name);
    PositionEntity getPositionById(Long id);
    PositionPageRespone getAllPositions(String keyword, String sort, int page, int size);
}
