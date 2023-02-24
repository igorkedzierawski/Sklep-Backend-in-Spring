package pl.sklep.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BuyerCannotAffordPurchaseException extends RuntimeException {

    public BuyerCannotAffordPurchaseException() {
        super("Kupujący nie ma wystarczająco środków na koncie aby dokonać tego zakupu");
    }

}
