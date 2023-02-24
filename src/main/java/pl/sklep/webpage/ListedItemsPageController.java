package pl.sklep.webpage;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sklep.listeditem.ListedItemService;
import pl.sklep.shopuser.ShopUser;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class ListedItemsPageController implements ModelInitializer {

    private final ListedItemService listedItemService;

    @GetMapping("/listed_items")
    public String getListedItemsPage(@AuthenticationPrincipal ShopUser user, Model model) {
        initModel(model, user);
        model.addAttribute("mainDisplay", "listed_items");
        model.addAttribute("items", listedItemService.getUsersListedItems(user, Pageable.ofSize(100)));
        return "base";
    }

}
