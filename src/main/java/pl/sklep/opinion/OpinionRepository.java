package pl.sklep.opinion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sklep.listeditem.ListedItem;
import pl.sklep.purchaseditem.PurchasedItem;
import pl.sklep.shopuser.ShopUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    @Query("SELECT o FROM opinion o WHERE o.purchasedItem = ?1")
    Optional<Opinion> findByPurchasedItem(PurchasedItem purchasedItem);

    @Query("SELECT o FROM opinion o WHERE o.purchasedItem IN ?1")
    List<Opinion> findByPurchasedItems(List<PurchasedItem> purchasedItems);

    @Query("SELECT o FROM opinion o WHERE o.purchasedItem.listedItem = ?1")
    Page<Opinion> findOpinionsByListedItem(ListedItem listedItem, Pageable page);

    @Query("SELECT o FROM opinion o WHERE o.purchasedItem.buyer = ?1")
    Page<Opinion> findOpinionsByBuyer(ShopUser buyer, Pageable page);

    @Query("SELECT o FROM opinion o WHERE o.purchasedItem.buyer = ?1")
    List<Opinion> findAllOpinionsByBuyer(ShopUser buyer);

    @Query("SELECT o FROM opinion o WHERE o.purchasedItem.listedItem.seller = ?1")
    Page<Opinion> findOpinionsBySeller(ShopUser seller, Pageable page);

}
