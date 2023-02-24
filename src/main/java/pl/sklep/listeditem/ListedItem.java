package pl.sklep.listeditem;

import jakarta.persistence.*;
import lombok.*;
import pl.sklep.shopuser.ShopUser;

@Entity(name = "listed_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ListedItem {

    @Id
    @SequenceGenerator(
            name = "listed_item_seqgen",
            sequenceName = "listed_item_seqgen",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "listed_item_seqgen"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(
            name = "seller_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "item_seller_fk"
            )
    )
    private ShopUser seller;

    public ListedItem(String name, Integer price, Integer stock, ShopUser seller) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.seller = seller;
    }

}
