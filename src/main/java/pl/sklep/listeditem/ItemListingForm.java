package pl.sklep.listeditem;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemListingForm {

    @NotNull(message = "Id towaru jest wymagana", groups = {UpdatingListing.class})
    private Long listedItemId;

    @NotNull(message = "Nazwa towaru jest wymagana", groups = {CreatingListing.class, UpdatingListing.class})
    @Size(min = 4, message = "Nazwa towaru musi składać się z conajmniej 4 znaków", groups = {CreatingListing.class, UpdatingListing.class})
    @Size(max = 100, message = "Nazwa towaru może składać się z maksymalnie 100 znaków", groups = {CreatingListing.class, UpdatingListing.class})
    private String name;

    @NotNull(message = "Cena za sztukę sztuk jest wymagana", groups = {CreatingListing.class, UpdatingListing.class})
    @Positive(message = "Cena musi być dodatnia", groups = {CreatingListing.class, UpdatingListing.class})
    @Max(value = 1_000_000_00, message = "Cena nie może przekroczyć 1,000,000.00", groups = {CreatingListing.class, UpdatingListing.class})
    private Integer price;

    @NotNull(message = "Liczba sprzedawanych sztuk jest wymagana", groups = {CreatingListing.class, UpdatingListing.class})
    @Positive(message = "Liczba sztuk musi być dodatnia", groups = {CreatingListing.class, UpdatingListing.class})
    @Max(value = 10_000, message = "Maksymalnie możesz wystawić na sprzedaż 10,000 sztuk towaru", groups = {CreatingListing.class, UpdatingListing.class})
    private Integer stock;

    public ItemListingForm(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public interface CreatingListing {
    }

    public interface UpdatingListing {
    }

}
