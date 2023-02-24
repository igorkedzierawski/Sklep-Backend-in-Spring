package pl.sklep.shopuser;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity(name = "shop_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ShopUser implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "shopuser_seqgen",
            sequenceName = "shopuser_seqgen",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shopuser_seqgen"
    )
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;
    @Column(nullable = false, updatable = false)
    private String password;
    @Column(nullable = false, updatable = false)
    private String role;
    @Column(nullable = false)
    private Integer balance;

    public ShopUser(String username, String password, String role, Integer balance) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
