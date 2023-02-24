package pl.sklep.opinion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class OpinionInteractionNotByBuyerExcepiton extends RuntimeException {

    public OpinionInteractionNotByBuyerExcepiton() {
        super("Musisz być kupcem tego przedmiotu aby móc wystawić opinię");
    }

}
