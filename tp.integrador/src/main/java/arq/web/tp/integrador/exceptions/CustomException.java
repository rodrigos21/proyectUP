package arq.web.tp.integrador.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;

    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

}