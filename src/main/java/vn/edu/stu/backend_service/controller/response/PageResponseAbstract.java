package vn.edu.stu.backend_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class PageResponseAbstract {
    private long pageNumber;
    private long pageSize;
    private long totalPages;
    private long totalElements;
}
