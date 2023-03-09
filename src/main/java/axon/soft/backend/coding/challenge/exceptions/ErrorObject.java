package axon.soft.backend.coding.challenge.exceptions;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ErrorObject{
    private String message;
    private int errorCode;

    public ErrorObject(){};

    public ErrorObject(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
