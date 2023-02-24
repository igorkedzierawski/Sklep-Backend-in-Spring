package pl.sklep.webapi;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sklep.shopping.PurchasedItemForm;
import pl.sklep.shopping.ShoppingService;
import pl.sklep.shopuser.ShopUser;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class PurchaseController {

    private final ShoppingService shoppingService;

    @Operation(description = "Wykonaj operację zakupu przedmiotu. " +
            "Może być wywołane przez zalogowanego użytkownika. "
    )
    @ResponseBody
    @PostMapping(value = "purchase", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_VALUE)
    public String purchase(@Valid PurchasedItemForm purchasedItemForm, @AuthenticationPrincipal ShopUser user) {
        shoppingService.tryToPerformPurchase(user, purchasedItemForm);
        return "{}";
    }

}
