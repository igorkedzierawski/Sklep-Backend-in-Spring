package pl.sklep.purchaseditem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sklep.shopuser.ShopUser;

public interface PurchasedItemRepository extends JpaRepository<PurchasedItem, Long> {

    @Query("SELECT i FROM purchased_item i WHERE i.buyer = ?1")
    Page<PurchasedItem> getItemsPurchasedBy(ShopUser user, Pageable page);

    @Query("SELECT i FROM purchased_item i WHERE i.listedItem.seller = ?1")
    Page<PurchasedItem> getItemsSoldBy(ShopUser user, Pageable page);

}
