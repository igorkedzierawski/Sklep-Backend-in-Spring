package pl.sklep.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PriceAtPurchaseDiscrepencyException extends RuntimeException {

    public PriceAtPurchaseDiscrepencyException(int expected, int actual) {
        super("Cena za sztukę kupowanego towaru wynosi " + expected + " zamiast " + actual +
                ". Czy cena sprzedawanego towaru się nie zmieniła?"
        );
    }

}
