package vn.edu.stu.backend_service.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContractType {
    PROBATION, OFFICIAL, PART_TIME, INTERN;

    @JsonCreator
    public static ContractType fromString(String value) {
        return ContractType.valueOf(value.toUpperCase());
    }
}
