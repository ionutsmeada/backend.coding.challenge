package axon.soft.backend.coding.challenge.controller.exception;

import axon.soft.backend.coding.challenge.exceptions.BadRequestException;
import axon.soft.backend.coding.challenge.exceptions.ErrorObject;
import axon.soft.backend.coding.challenge.exceptions.NotFoundException;
import axon.soft.backend.coding.challenge.helper.ErrorType;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Component
@ControllerAdvice
public class CustomExceptionHandler {
    ErrorObject errorObject;
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorObject> notFoundCustomException(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getErrorObject());
    }
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorObject> badRequestCustomException(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrorObject());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorObject> handleConstraintExceptions(ConstraintViolationException e){
        errorObject = new ErrorObject(ErrorType.ERROR_CODE_6.getMessage(), ErrorType.ERROR_CODE_6.getCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorObject);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorObject> handleWrongMethodExceptions(Exception e){
        errorObject = new ErrorObject("Method not allowed", HttpStatus.METHOD_NOT_ALLOWED.value());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorObject);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorObject> handleAllExceptions(Exception e){
        errorObject = new ErrorObject(ErrorType.ERROR_CODE_0.getMessage(), ErrorType.ERROR_CODE_0.getCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorObject);
    }
}
