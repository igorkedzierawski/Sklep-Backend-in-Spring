package pl.sklep.opinion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OpinionTooLongException extends RuntimeException {

    public static final int OPINION_MAX_LENGTH = 300;

    public OpinionTooLongException() {
        super("Długość opini nie może przekroczyć " + OPINION_MAX_LENGTH + " znaków");
    }

}
