package pl.sklep.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {

    private final ShopUserRepository shopUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return shopUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie istnieje " + username));
    }

}
