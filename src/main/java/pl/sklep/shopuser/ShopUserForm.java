package pl.sklep.shopuser;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ShopUserForm {

    @NotEmpty(message = "Nazwa użytkownika nie może być pusta")
    @Size(min = 4, message = "Nazwa użytkownika musi składać się z conajmniej 4 znaków")
    @Size(max = 30, message = "Nazwa użytkownika może składać się z maksymalnie 30 znaków")
    private String username;

    @NotEmpty(message = "Hasło nie może być puste")
    @Size(min = 2, message = "Hasło musi składać się z conajmniej 2 znaków")
    @Size(max = 100, message = "Hasło może składać się z maksymalnie 100 znaków")
    private String password;

}
