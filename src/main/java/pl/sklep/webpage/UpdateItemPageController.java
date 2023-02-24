package pl.sklep.webpage;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sklep.Util;
import pl.sklep.listeditem.ListedItem;
import pl.sklep.listeditem.ListedItemService;
import pl.sklep.shopuser.ShopUser;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UpdateItemPageController implements ModelInitializer {

    private final ListedItemService listedItemService;

    @GetMapping("/update_item/{itemId:\\d+}")
    public String getSellItemPage(@PathVariable String itemId, @AuthenticationPrincipal ShopUser user, Model model) {
        initModel(model, user);
        model.addAttribute("mainDisplay", "update_item");

        ListedItem listedItem = listedItemService.getListedItem(Util.parseLong(itemId)
                .orElseThrow(() -> new IllegalArgumentException("'" + itemId + "' jest niepoprawnym id")));
        model.addAttribute("listedItem", listedItem);
        return "base";
    }

}
