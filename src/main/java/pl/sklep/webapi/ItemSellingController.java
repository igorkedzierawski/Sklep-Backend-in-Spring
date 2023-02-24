package pl.sklep.webapi;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sklep.listeditem.ItemListingForm;
import pl.sklep.listeditem.ListedItemService;
import pl.sklep.shopuser.ShopUser;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static pl.sklep.listeditem.ItemListingForm.CreatingListing;
import static pl.sklep.listeditem.ItemListingForm.UpdatingListing;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class ItemSellingController {

    private final ListedItemService listedItemService;

    @Operation(description = "Wykonaj operację wystawienia przedmiotu na sprzedaż. " +
            "Wartość pola listedItemId jest ignorowana. " +
            "Może zostać wykonane przez zalogowanego użytkownika. "
    )
    @ResponseBody
    @PostMapping(value = "sell_item", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_VALUE)
    public String sellItem(@Validated({CreatingListing.class}) ItemListingForm creationForm, @AuthenticationPrincipal ShopUser user) {
        listedItemService.tryToListItem(user, creationForm);
        return "{}";
    }

    @Operation(description = "Wykonaj operację zaktualizowania informacji o przedmiocie wystawionym na sprzedaż. " +
            "Może zostać wykonane przez użytkownika, który sprzedaje tenże przedmiot. "
    )
    @ResponseBody
    @PostMapping(value = "update_item", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_VALUE)
    public String updateItem(@Validated({UpdatingListing.class}) ItemListingForm updateForm, @AuthenticationPrincipal ShopUser user) {
        listedItemService.tryToUpdateListedItem(user, updateForm);
        return "{}";
    }

}
