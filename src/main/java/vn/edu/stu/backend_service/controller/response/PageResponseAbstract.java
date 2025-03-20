package vn.edu.stu.backend_service.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class PageResponseAbstract {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalElements;
}
