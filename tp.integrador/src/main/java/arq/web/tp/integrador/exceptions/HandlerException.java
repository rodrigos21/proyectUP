package arq.web.tp.integrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        HttpStatus status = e.getStatus();

        CustomExceptionResponse response = new CustomExceptionResponse(
                e.getMessage(),
                status.value()
        );
        return new ResponseEntity<>(response, status);
    }

    public static class CustomExceptionResponse {
        private String message;
        private int status;

        public CustomExceptionResponse(String message, int status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
