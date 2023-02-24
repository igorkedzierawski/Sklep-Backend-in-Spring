package pl.sklep.webpage;

import org.springframework.ui.Model;
import pl.sklep.shopuser.ShopUser;

public interface ModelInitializer {

    default void initModel(Model model, ShopUser user) {
        if (user == null) {
            model.addAttribute("usermenuState", "login");
        } else {
            model.addAttribute("usermenuState", "logged_in");
            model.addAttribute("user", user);
        }
    }

}
