package pl.sklep.listeditem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.sklep.shopuser.ShopUser;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ListedItemUpdatingNotBySellerException extends RuntimeException {

    public ListedItemUpdatingNotBySellerException(ShopUser updatingUser, Long listedItemId) {
        super("Użytkonik " + updatingUser.getUsername() + " nie może zaktualizować przedmiotu o id " + listedItemId);
    }

}
