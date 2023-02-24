package pl.sklep.webapi;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sklep.shopuser.ShopUser;
import pl.sklep.shopuser.ShopUserForm;
import pl.sklep.shopuser.ShopUserService;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserRegistrationController {

    private final ShopUserService shopUserService;

    @Operation(description = "Wykonaj operację zarejestrowania nowego użytkownika. " +
            "Może być wywołane przez niezalogowanych użytkowników. ")
    @ResponseBody
    @PostMapping(value = "/register", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_VALUE)
    public String register(@Valid ShopUserForm shopUserForm, @AuthenticationPrincipal ShopUser user) {
        if (user != null) {
            throw new IllegalStateException("Nie możesz się rejestrować będąc zalogowanym");
        }
        shopUserService.createUser(shopUserForm, "USER", 2_000_00);
        return "{}";
    }

}
