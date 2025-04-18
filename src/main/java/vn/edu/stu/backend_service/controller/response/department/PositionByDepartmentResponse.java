package vn.edu.stu.backend_service.controller.response.department;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.position.PositionResponse;
import java.util.List;

@Getter
@Setter
public class PositionByDepartmentResponse {
    List<PositionResponse> positions;
}
