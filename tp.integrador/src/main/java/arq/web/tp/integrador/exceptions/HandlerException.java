package arq.web.tp.integrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleCustomException(CustomException e, HttpStatus status){
        CustomException ce = new CustomException(
                e.getMessage(),
                status
        );
        return new ResponseEntity<>(ce, status);
    }
}
