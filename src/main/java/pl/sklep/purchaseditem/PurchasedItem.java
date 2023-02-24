package pl.sklep.purchaseditem;

import jakarta.persistence.*;
import lombok.*;
import pl.sklep.listeditem.ListedItem;
import pl.sklep.shopuser.ShopUser;

@Entity(name = "purchased_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PurchasedItem {

    @Id()
    @SequenceGenerator(
            name = "purchased_item_seqgen",
            sequenceName = "purchased_item_seqgen",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchased_item_seqgen"
    )
    private Long id;

    @Column(nullable = false, updatable = false)
    private Integer priceAtPurchase;

    @Column(nullable = false, updatable = false)
    private Integer count;

    @OneToOne
    @JoinColumn(
            name = "listed_item_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "listed_item_item_fk"
            ))
    private ListedItem listedItem;

    @ManyToOne
    @JoinColumn(
            name = "buyer_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "purchased_item_buyer_fk"
            ))
    private ShopUser buyer;

    public PurchasedItem(Integer priceAtPurchase, Integer count, ListedItem listedItem, ShopUser buyer) {
        this.priceAtPurchase = priceAtPurchase;
        this.count = count;
        this.listedItem = listedItem;
        this.buyer = buyer;
    }

}
