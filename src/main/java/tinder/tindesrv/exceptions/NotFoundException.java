package tinder.tindesrv.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Records weren't found");
    }
}
