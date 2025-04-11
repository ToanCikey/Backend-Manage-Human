package vn.edu.stu.backend_service.controller.response.position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PositionDetailResponse {
    private String positionName;
    private Long employeeCount;
}
