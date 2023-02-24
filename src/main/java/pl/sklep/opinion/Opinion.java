package pl.sklep.opinion;

import jakarta.persistence.*;
import lombok.*;
import pl.sklep.purchaseditem.PurchasedItem;

@Entity(name = "opinion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Opinion {

    @Id
    @SequenceGenerator(
            name = "opinion_seqgen",
            sequenceName = "opinion_seqgen",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "opinion_seqgen",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToOne
    @JoinColumn(
            name = "purchased_item_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "purchase_opinion_fk")
    )
    private PurchasedItem purchasedItem;

    public Opinion(String content, PurchasedItem purchasedItem) {
        this.content = content;
        this.purchasedItem = purchasedItem;
    }

}
