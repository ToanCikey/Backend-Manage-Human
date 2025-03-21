package vn.edu.stu.backend_service.controller.response.position;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.PageResponseAbstract;

import java.util.List;
@Getter
@Setter
public class PositionPageRespone extends PageResponseAbstract {
    List<PositionResponse> positions;
}
