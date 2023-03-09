package axon.soft.backend.coding.challenge.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    private final ErrorObject errorObject;
    public BadRequestException(ErrorObject error){
        this.errorObject = error;
    }
}
