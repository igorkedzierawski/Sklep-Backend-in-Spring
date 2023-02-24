package pl.sklep.listeditem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sklep.shopuser.ShopUser;

public interface ListedItemRepository extends JpaRepository<ListedItem, Long> {

    @Query("SELECT i FROM listed_item i WHERE i.seller = :user")
    Page<ListedItem> findListedItemsListedByUser(@Param("user") ShopUser user, Pageable page);

    @Query("SELECT i FROM listed_item i WHERE POSITION(LOWER(?1) IN LOWER(i.name)) > 0")
    Page<ListedItem> findListedItemsWithSubsequenceInTheirNames(String subsequence, Pageable page);

    @Query("SELECT i FROM listed_item i")
    Page<ListedItem> findAllListedItems(Pageable page);

}
