package tinder.tindesrv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PictureServiceException extends ResponseStatusException {
    public PictureServiceException(Exception e) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Service send nothing!", e);
    }
}
