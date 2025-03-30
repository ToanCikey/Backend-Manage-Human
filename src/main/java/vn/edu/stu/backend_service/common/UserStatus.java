package vn.edu.stu.backend_service.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserStatus {
    NONE, ACTIVE, INACTIVE;

    @JsonCreator
    public static UserStatus fromString(String value) {
        return UserStatus.valueOf(value.toUpperCase());
    }
}
