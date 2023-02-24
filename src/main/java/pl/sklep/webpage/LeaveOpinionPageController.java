package pl.sklep.webpage;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sklep.Util;
import pl.sklep.purchaseditem.PurchasedItem;
import pl.sklep.shopping.ShoppingService;
import pl.sklep.shopuser.ShopUser;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class LeaveOpinionPageController implements ModelInitializer {

    private final ShoppingService shoppingService;

    @GetMapping("/opinion")
    public String getSellItemPage(@RequestParam("purchased_item_id") String purchasedItemId, @AuthenticationPrincipal ShopUser user, Model model) {
        initModel(model, user);
        model.addAttribute("mainDisplay", "leave_opinion");

        PurchasedItem purchasedItem = shoppingService.getPurchasedItem(Util.parseLong(purchasedItemId)
                .orElseThrow(() -> new IllegalArgumentException("'" + purchasedItemId + "' jest niepoprawnym id")));

        model.addAttribute("purchasedItem", purchasedItem);
        return "base";
    }

}
