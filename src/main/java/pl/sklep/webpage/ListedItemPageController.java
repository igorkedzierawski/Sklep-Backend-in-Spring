package pl.sklep.webpage;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sklep.Util;
import pl.sklep.listeditem.ListedItem;
import pl.sklep.listeditem.ListedItemService;
import pl.sklep.opinion.OpinionService;
import pl.sklep.shopuser.ShopUser;

import java.util.Objects;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ListedItemPageController implements ModelInitializer {

    private final ListedItemService listedItemService;
    private final OpinionService opinionService;

    @GetMapping("/item/{itemId:\\d+}")
    public String getListedItemPage(@PathVariable String itemId, @AuthenticationPrincipal ShopUser user, Model model) {
        initModel(model, user);
        model.addAttribute("mainDisplay", "item_page");

        ListedItem listedItem = listedItemService.getListedItem(Util.parseLong(itemId)
                .orElseThrow(() -> new IllegalArgumentException("'" + itemId + "' jest niepoprawnym id")));
        model.addAttribute("listedItem", listedItem);
        model.addAttribute("opinions", opinionService.getOpinionsForListedItem(listedItem, Pageable.ofSize(100)));
        if (user == null) {
            model.addAttribute("perspective", "anonymous");
        } else if (Objects.equals(user.getId(), listedItem.getSeller().getId())) {
            model.addAttribute("perspective", "seller");
        } else {
            model.addAttribute("perspective", "buyer");
            boolean canBuy = user.getBalance() >= listedItem.getPrice();
            model.addAttribute("canBuy", canBuy);
            if (canBuy) {
                model.addAttribute("maxPieces", Math.min(
                        listedItem.getStock(),
                        user.getBalance() / listedItem.getPrice()
                ));
            }
        }
        return "base";
    }

}
