package pl.sklep.opinion;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sklep.listeditem.ListedItem;
import pl.sklep.opinion.exception.OpinionAlreadyPresentException;
import pl.sklep.opinion.exception.OpinionInteractionNotByBuyerExcepiton;
import pl.sklep.opinion.exception.OpinionNotFoundExcepiton;
import pl.sklep.purchaseditem.PurchasedItem;
import pl.sklep.shopuser.ShopUser;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final OpinionFormAdapter opinionFormAdapter;

    public Opinion getOpinion(Long id) {
        return opinionRepository.findById(id)
                .orElseThrow(() -> new OpinionNotFoundExcepiton(id));
    }

    public Opinion getOpinionForPurchasedItem(PurchasedItem purchasedItem) {
        return opinionRepository.findByPurchasedItem(purchasedItem)
                .orElseThrow(() -> new OpinionNotFoundExcepiton(purchasedItem));
    }

    public List<Opinion> getOpinionsForPurchasedItems(List<PurchasedItem> purchasedItems) {
        return opinionRepository.findByPurchasedItems(purchasedItems);
    }

    public Page<Opinion> getOpinionsForListedItem(ListedItem listedItem, Pageable page) {
        return opinionRepository.findOpinionsByListedItem(listedItem, page);
    }

    public Page<Opinion> getOpinionsForBuyer(ShopUser buyer, Pageable page) {
        return opinionRepository.findOpinionsByBuyer(buyer, page);
    }

    public Page<Opinion> getOpinionsForSeller(ShopUser seller, Pageable page) {
        return opinionRepository.findOpinionsBySeller(seller, page);
    }

    public Opinion tryToLeaveOpinion(OpinionForm opinionForm, ShopUser user) {
        Opinion opinion = opinionFormAdapter.createOpinionFromForm(opinionForm);
        if (!Objects.equals(opinion.getPurchasedItem().getBuyer().getId(), user.getId())) {
            throw new OpinionInteractionNotByBuyerExcepiton();
        }
        if (opinionRepository.findAllOpinionsByBuyer(user).stream()
                .map(Opinion::getPurchasedItem).map(PurchasedItem::getId)
                .anyMatch(i -> Objects.equals(opinion.getPurchasedItem().getId(), i))
        ) {
            throw new OpinionAlreadyPresentException();
        }
        opinionRepository.save(opinion);
        return opinion;
    }

    public void tryToDeleteOpinion(Long id, ShopUser user) {
        Opinion opinion = getOpinion(id);
        if (!Objects.equals(opinion.getPurchasedItem().getBuyer().getId(), user.getId())) {
            throw new OpinionInteractionNotByBuyerExcepiton();
        }
        opinionRepository.delete(opinion);
    }

    public void deleteOpinion(Opinion opinion) {
        opinionRepository.delete(opinion);
    }

}
