package pl.sklep.opinion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static pl.sklep.opinion.exception.OpinionTooLongException.OPINION_MAX_LENGTH;

@AllArgsConstructor
@Getter
@Setter
public class OpinionForm {

    @NotNull(message = "Id zakupu, dla której wystawiasz opinię jest wymagane")
    private Long purchasedItemId;

    @NotNull(message = "Zawartość opinii jest wymagana")
    @Size(max = OPINION_MAX_LENGTH, message = "Długość opinii nie może przekroczyć 300 znaków")
    private String content;

}
