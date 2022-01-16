package desafioPubfuture.desafioPubfuture.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InexistenteException extends RuntimeException {

    public InexistenteException(String message) {
        super(message);
    }

}
