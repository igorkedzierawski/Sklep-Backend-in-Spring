package pl.sklep.webpage;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sklep.shopping.ShoppingService;
import pl.sklep.shopuser.ShopUser;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class SoldItemsPageController implements ModelInitializer {

    private final ShoppingService shoppingService;

    @GetMapping("/sold_items")
    public String getSoldItemsPage(@AuthenticationPrincipal ShopUser user, Model model) {
        initModel(model, user);
        model.addAttribute("mainDisplay", "sold_items");
        model.addAttribute("items", shoppingService.getItemsSoldBy(user, Pageable.ofSize(100)));
        return "base";
    }

}
