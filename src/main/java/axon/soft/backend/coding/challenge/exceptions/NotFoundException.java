package axon.soft.backend.coding.challenge.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class NotFoundException extends RuntimeException{

    private final ErrorObject errorObject;
    public NotFoundException( ErrorObject error){
        this.errorObject = error;
    }
}
