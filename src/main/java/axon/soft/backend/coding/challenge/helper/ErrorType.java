package axon.soft.backend.coding.challenge.helper;

import lombok.Getter;

@Getter
public enum ErrorType {
    ERROR_CODE_0(0, "Error code 0"),
    ERROR_CODE_2(2, "Some room numbers are duplicated"),
    ERROR_CODE_3(3, "Some residents are duplicated"),
    ERROR_CODE_4(4, "Some CSV lines are not valid"),
    ERROR_CODE_5(5, "Room number does not exists"),
    ERROR_CODE_6(6, "Room number must have 4 digits");
    private final int code;

    private final String message;

    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
