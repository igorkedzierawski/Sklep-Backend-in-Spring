package pl.sklep.shopping;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PurchasedItemForm {

    @NotNull(message = "Id kupowanego przedmiotu jest wymagane")
    private Long listedItemId;

    @NotNull(message = "Liczba kupowanych przedmiotów jest wymagana")
    @Positive(message = "Liczba kupowanych przedmiotów musi być dodatnia")
    private Integer pieces;

    @NotNull(message = "Chwilowa cena kupowanego towaru jest wymagana")
    @Positive(message = "Chwilowa cena kupowanego towaru musi być dodatnia")
    private Integer priceAtPurchase;

}
