package pl.sklep.shopuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {

    @Query("SELECT u FROM shop_user u WHERE u.username = ?1")
    Optional<ShopUser> findByUsername(String username);

    @Query("Select u FROM shop_user u")
    List<ShopUser> findAllUsers();

}
