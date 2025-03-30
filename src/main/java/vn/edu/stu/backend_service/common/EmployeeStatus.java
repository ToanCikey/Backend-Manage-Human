package vn.edu.stu.backend_service.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EmployeeStatus {
    WORKING, RESIGNED;

    @JsonCreator
    public static EmployeeStatus fromString(String value) {
        return EmployeeStatus.valueOf(value.toUpperCase());
    }
}
