package pl.sklep.webpage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sklep.Util;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class LoginAndRegisterPageController {

    @GetMapping("/login")
    public String getLogin(@RequestParam(name = "err", required = false, defaultValue = "false") String err, Model model) {
        Optional<Boolean> optError = Util.parseBool(err);
        model.addAttribute("errStyle", "display: none");
        if (optError.isPresent() && optError.get()) {
            model.addAttribute("errStyle", "");
        }
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(@RequestParam(name = "err", required = false, defaultValue = "false") String err, Model model) {
        Optional<Boolean> optError = Util.parseBool(err);
        model.addAttribute("errStyle", "display: none");
        if (optError.isPresent() && optError.get()) {
            model.addAttribute("errStyle", "");
        }
        return "register";
    }

}
