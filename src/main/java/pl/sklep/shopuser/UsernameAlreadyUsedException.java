package pl.sklep.shopuser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyUsedException extends RuntimeException {

    public UsernameAlreadyUsedException(String username) {
        super("Użytkownik o nazwie " + username + " już istnieje");
    }

}
