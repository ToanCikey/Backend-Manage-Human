package vn.edu.stu.backend_service.controller.response.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.stu.backend_service.common.ContractType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponse {
    private Long id;

    private ContractType contractType;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
