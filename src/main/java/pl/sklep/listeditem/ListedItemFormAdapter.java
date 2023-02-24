package pl.sklep.listeditem;

import org.springframework.stereotype.Service;
import pl.sklep.shopuser.ShopUser;

@Service
public class ListedItemFormAdapter {

    public ListedItem createListedItemFromForm(ItemListingForm itemListingForm, ShopUser seller) {
        return new ListedItem(
                itemListingForm.getName(), itemListingForm.getPrice(), itemListingForm.getStock(), seller
        );
    }

    public void updateListedItemWithForm(ListedItem item, ItemListingForm itemListingForm) {
        item.setName(itemListingForm.getName());
        item.setPrice(itemListingForm.getPrice());
        item.setStock(itemListingForm.getStock());
    }

}
