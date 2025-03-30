package vn.edu.stu.backend_service.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
    ADMIN, USER;

    @JsonCreator
    public static UserType fromString(String value) {
        return UserType.valueOf(value.toUpperCase());
    }
}
