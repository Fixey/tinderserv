package tinder.tindesrv.exceptions;

public class CreatePersonException extends RuntimeException {
    public CreatePersonException() {
        super("Can't create Person");
    }
}
