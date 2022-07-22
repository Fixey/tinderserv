package tinder.tindesrv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmptyPictureException extends ResponseStatusException {
    public EmptyPictureException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Picture service send nothing!");
    }
}
