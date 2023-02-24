package pl.sklep.opinion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sklep.shopping.ShoppingService;

@Component
@AllArgsConstructor
public class OpinionFormAdapter {

    private final ShoppingService shoppingService;

    public Opinion createOpinionFromForm(OpinionForm form) {
        return new Opinion(form.getContent(), shoppingService.getPurchasedItem(form.getPurchasedItemId()));
    }

}
