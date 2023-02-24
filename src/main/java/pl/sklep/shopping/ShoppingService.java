package pl.sklep.shopping;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sklep.listeditem.ListedItem;
import pl.sklep.listeditem.ListedItemRepository;
import pl.sklep.listeditem.ListedItemService;
import pl.sklep.purchaseditem.PurchasedItem;
import pl.sklep.purchaseditem.PurchasedItemRepository;
import pl.sklep.shopping.exception.*;
import pl.sklep.shopuser.ShopUser;
import pl.sklep.shopuser.ShopUserRepository;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ShoppingService {

    private final ShopUserRepository shopUserRepository;
    private final ListedItemRepository listedItemRepository;
    private final PurchasedItemRepository purchasedItemRepository;

    private final ListedItemService listedItemService;

    public Page<PurchasedItem> getItemsPurchasedBy(ShopUser buyer, Pageable page) {
        return purchasedItemRepository.getItemsPurchasedBy(buyer, page);
    }

    public Page<PurchasedItem> getItemsSoldBy(ShopUser seller, Pageable page) {
        return purchasedItemRepository.getItemsSoldBy(seller, page);
    }

    public PurchasedItem getPurchasedItem(Long id) {
        return purchasedItemRepository.findById(id).orElseThrow(() -> new PurchasedItemNotFoundException(id));
    }

    @Transactional
    public PurchasedItem tryToPerformPurchase(ShopUser buyer, PurchasedItemForm form) {
        ListedItem listedItem = listedItemService.getListedItem(form.getListedItemId());
        if (Objects.equals(listedItem.getSeller().getId(), buyer.getId())) {
            throw new BuyerPurchasesTheirOwnItemException();
        }
        if (form.getPieces() > listedItem.getStock()) {
            throw new TooLittlePiecesInStockException(listedItem.getStock(), form.getPieces());
        }
        Integer expectedPriceAtPurchase = listedItem.getPrice();
        if (!Objects.equals(expectedPriceAtPurchase, form.getPriceAtPurchase())) {
            throw new PriceAtPurchaseDiscrepencyException(expectedPriceAtPurchase, form.getPriceAtPurchase());
        }
        int totalPrice = expectedPriceAtPurchase * form.getPieces();
        if (buyer.getBalance() < totalPrice) {
            throw new BuyerCannotAffordPurchaseException();
        }
        ShopUser seller = listedItem.getSeller();
        PurchasedItem purchasedItem = new PurchasedItem(expectedPriceAtPurchase, form.getPieces(), listedItem, buyer);
        buyer.setBalance(buyer.getBalance() - totalPrice);
        seller.setBalance(seller.getBalance() + totalPrice);
        listedItem.setStock(listedItem.getStock() - form.getPieces());
        purchasedItemRepository.save(purchasedItem);
        listedItemRepository.save(listedItem);
        shopUserRepository.save(buyer);
        shopUserRepository.save(seller);
        return purchasedItem;
    }

}
