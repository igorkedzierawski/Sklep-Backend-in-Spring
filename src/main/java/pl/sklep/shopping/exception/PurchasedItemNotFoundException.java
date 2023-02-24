package pl.sklep.shopping.exception;

public class PurchasedItemNotFoundException extends RuntimeException {

    public PurchasedItemNotFoundException(Long id) {
        super("Nie znalezionu wpisu kupna towaru o id " + id);
    }
}
