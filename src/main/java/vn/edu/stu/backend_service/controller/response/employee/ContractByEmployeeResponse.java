package vn.edu.stu.backend_service.controller.response.employee;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.contract.ContractResponse;

import java.util.List;

@Getter
@Setter
public class ContractByEmployeeResponse {
    List<ContractResponse> contracts;
}
