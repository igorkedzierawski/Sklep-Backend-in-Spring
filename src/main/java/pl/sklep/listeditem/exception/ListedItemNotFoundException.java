package pl.sklep.listeditem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListedItemNotFoundException extends RuntimeException {

    public ListedItemNotFoundException(Long id) {
        super("Nie znaleziono przedmiotu wystawionego na sprzedaż z id " + id);
    }

}
