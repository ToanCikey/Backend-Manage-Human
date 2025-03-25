package vn.edu.stu.backend_service.controller.response.contract;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.PageResponseAbstract;

import java.util.List;

@Getter
@Setter
public class ContractPageResponse extends PageResponseAbstract {
    List<ContractResponse> contracts;
}
