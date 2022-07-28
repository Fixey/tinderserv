package tinder.tindesrv.exceptions;

public class NotFountException extends RuntimeException {
    public NotFountException() {
        super("Records weren't found");
    }
}
