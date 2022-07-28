package bg.lease.model.exceptions;

public class WrongLeaseStatusException extends RuntimeException{

    public WrongLeaseStatusException(String message) {
        super(message);
    }

    public WrongLeaseStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
