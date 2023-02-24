package pl.sklep.webapi;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.sklep.Util;
import pl.sklep.opinion.OpinionForm;
import pl.sklep.opinion.OpinionService;
import pl.sklep.shopuser.ShopUser;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;

    @Operation(description = "Wykonaj operację dodania opinii o zakupionym przedmiocie. " +
            "Może być wywołane przez użytkownika, który kupił tenże przedmiot. "
    )
    @PostMapping(value = "opinion", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String leaveOpinion(@Valid OpinionForm form, @AuthenticationPrincipal ShopUser user) {
        opinionService.tryToLeaveOpinion(form, user);
        return "{}";
    }

    @Operation(description = "Wykonaj operację usunięcia opinii o danym id." +
            "Może być wywołane przez użytkownika, który zostawił tąże opinię."
    )
    @ResponseBody
    @DeleteMapping(value = "opinion", consumes = MediaType.ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    public String deleteOpinion(@RequestParam("opinion_id") String purchasedItemId, @AuthenticationPrincipal ShopUser user) {
        Long id = Util.parseLong(purchasedItemId)
                .orElseThrow(() -> new IllegalArgumentException("'" + purchasedItemId + "' jest niepoprawnym id"));
        opinionService.tryToDeleteOpinion(id, user);
        return "{}";
    }

}
