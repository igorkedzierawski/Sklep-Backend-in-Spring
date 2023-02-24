package pl.sklep.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sklep.config.PasswordEncoder;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopUserService {

    private final ShopUserRepository shopUserRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<ShopUser> getUser(String username) {
        return shopUserRepository.findByUsername(username);
    }

    public Optional<ShopUser> getUser(Long id) {
        return shopUserRepository.findById(id);
    }

    public ShopUser createUser(ShopUserForm userForm, String role, Integer balance) {
        if (getUser(userForm.getUsername()).isPresent()) {
            throw new UsernameAlreadyUsedException(userForm.getUsername());
        }
        String encodedPassword = passwordEncoder.encode(userForm.getPassword());
        ShopUser user = new ShopUser(userForm.getUsername(), encodedPassword, role, balance);
        shopUserRepository.save(user);
        return user;
    }

    public void updateUsersBalance(ShopUser user, Integer balance) {
        user.setBalance(balance);
        shopUserRepository.save(user);
    }

}
