package pl.sklep.webpage;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sklep.listeditem.ListedItemRepository;
import pl.sklep.shopuser.ShopUser;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class SearchPageController implements ModelInitializer {

    private final ListedItemRepository listedItemRepository;

    @GetMapping("/search_for_items")
    public String getSearchForItemsPage(@Param("query") String query, @AuthenticationPrincipal ShopUser user, Model model) {
        initModel(model, user);
        model.addAttribute("mainDisplay", "search_for_items_display");
        model.addAttribute("query", query);
        model.addAttribute("items", listedItemRepository.findListedItemsWithSubsequenceInTheirNames(query, Pageable.ofSize(100)));
        return "base";
    }

}
