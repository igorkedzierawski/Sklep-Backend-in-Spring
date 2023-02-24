package pl.sklep.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BuyerPurchasesTheirOwnItemException extends RuntimeException {

    public BuyerPurchasesTheirOwnItemException() {
        super("Nie możesz kupić swojego własnego produktu");
    }

}
