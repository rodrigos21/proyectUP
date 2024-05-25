package arq.web.tp.integrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleCustomException(CustomException e, HttpStatus status) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        CustomException customException = new CustomException(
                e.getMessage(),
                badRequest);
        return new ResponseEntity<>(customException, badRequest);
    }
}
