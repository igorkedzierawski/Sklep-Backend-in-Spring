package pl.sklep.webpage;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sklep.shopuser.ShopUser;

@Controller
@RequestMapping("/user")
public class SellItemPageController implements ModelInitializer {

    @GetMapping("/sell_item")
    public String getSellItemPage(@AuthenticationPrincipal ShopUser user, Model model) {
        initModel(model, user);
        model.addAttribute("mainDisplay", "sell_item");
        return "base";
    }

}
