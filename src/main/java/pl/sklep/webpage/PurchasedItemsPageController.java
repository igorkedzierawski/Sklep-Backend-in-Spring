package pl.sklep.webpage;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sklep.opinion.Opinion;
import pl.sklep.opinion.OpinionService;
import pl.sklep.purchaseditem.PurchasedItem;
import pl.sklep.shopping.ShoppingService;
import pl.sklep.shopuser.ShopUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class PurchasedItemsPageController implements ModelInitializer {

    private final ShoppingService shoppingService;
    private final OpinionService opinionService;

    @GetMapping("/purchased_items")
    public String getPurchasedItemsPage(@AuthenticationPrincipal ShopUser user, Model model) {
        initModel(model, user);
        model.addAttribute("mainDisplay", "purchased_items");
        Page<PurchasedItem> itemsPurchasedBy = shoppingService.getItemsPurchasedBy(user, Pageable.ofSize(100));
        Map<Long, Long> itemIdToOpinionIdMap = new HashMap<>();
        List<Opinion> opinionsForPurchasedItems = opinionService.getOpinionsForPurchasedItems(itemsPurchasedBy.getContent());
        for (Opinion opinion : opinionsForPurchasedItems) {
            itemIdToOpinionIdMap.put(opinion.getPurchasedItem().getId(), opinion.getId());
        }
        List<Long> opinionPresentItemIds = opinionsForPurchasedItems.stream()
                .map(o -> o.getPurchasedItem().getId()).toList();
        model.addAttribute("items", itemsPurchasedBy);
        model.addAttribute("opinionPresentItemIds", opinionPresentItemIds);
        model.addAttribute("itemIdToOpinionIdMap", itemIdToOpinionIdMap);
        return "base";
    }

}
