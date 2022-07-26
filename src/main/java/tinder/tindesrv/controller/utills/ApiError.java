package tinder.tindesrv.controller.utills;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> stackTrace;

    public ApiError(HttpStatus status, String message, List<String> stackTrace) {
        super();
        this.status = status;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        stackTrace = List.of(error);
    }
}
