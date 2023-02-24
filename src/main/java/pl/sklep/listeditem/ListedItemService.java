package pl.sklep.listeditem;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sklep.listeditem.exception.ListedItemNotFoundException;
import pl.sklep.listeditem.exception.ListedItemUpdatingNotBySellerException;
import pl.sklep.shopuser.ShopUser;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ListedItemService {

    private final ListedItemRepository listedItemRepository;
    private final ListedItemFormAdapter listedItemFormAdapter;

    public ListedItem getListedItem(Long id) {
        return listedItemRepository.findById(id).orElseThrow(() -> new ListedItemNotFoundException(id));
    }

    public Page<ListedItem> getUsersListedItems(ShopUser user, Pageable page) {
        return listedItemRepository.findListedItemsListedByUser(user, page);
    }

    public Page<ListedItem> searchForListedItems(String searchQuery, Pageable page) {
        return listedItemRepository.findListedItemsWithSubsequenceInTheirNames(searchQuery, page);
    }

    public Page<ListedItem> getAllListedItems(Pageable page) {
        return listedItemRepository.findAllListedItems(page);
    }

    public ListedItem tryToListItem(ShopUser seller, ItemListingForm itemListingForm) {
        ListedItem listedItem = listedItemFormAdapter.createListedItemFromForm(itemListingForm, seller);
        listedItemRepository.save(listedItem);
        return listedItem;
    }

    public void tryToUpdateListedItem(ShopUser updatingUser, ItemListingForm itemListingForm) {
        ListedItem listedItem = getListedItem(itemListingForm.getListedItemId());
        if (!Objects.equals(updatingUser.getId(), listedItem.getSeller().getId())) {
            throw new ListedItemUpdatingNotBySellerException(updatingUser, listedItem.getId());
        }
        listedItemFormAdapter.updateListedItemWithForm(listedItem, itemListingForm);
        listedItemRepository.save(listedItem);
    }

}
