package bg.lease.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value={WrongLeaseStatusException.class})
    public ResponseEntity<Object> handleException(WrongLeaseStatusException e){
        HttpStatus httpStatus = HttpStatus.NOT_MODIFIED;
        CustomException customException = new CustomException(e.getMessage(),
                                                httpStatus,
                                                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(customException, httpStatus);
    }
}
