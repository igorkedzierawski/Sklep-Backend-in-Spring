package pl.sklep.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TooLittlePiecesInStockException extends RuntimeException {

    public TooLittlePiecesInStockException(int stock, int count) {
        super("Próbowano kupić " + count + " sztuk towaru kiedy dostępne jest tylko " + stock + " sztuk");
    }

}
