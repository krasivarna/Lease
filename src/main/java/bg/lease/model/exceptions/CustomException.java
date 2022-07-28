package bg.lease.model.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CustomException {

    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;

    public CustomException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
