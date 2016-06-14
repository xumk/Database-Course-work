package modal.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import modal.entity.Fish;
import modal.entity.Fisher;
import modal.entity.agregation.Color;
import modal.entity.agregation.TypeBait;

@Entity(
        name = "lure"
)
@Table(
        name = "lure"
)
public class Lure {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "id"
    )
    private Long id;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "number_hooks"
    )
    private Integer countHooks;
    @Column(
            name = "weight"
    )
    private Double weight;
    @Column(
            name = "diving_depth"
    )
    private Double divingDepth;
    @Column(
            name = "color"
    )
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column(
            name = "is_imitation"
    )
    @Enumerated(EnumType.ORDINAL)
    private TypeBait typeBait;
    @Column(
            name = "price"
    )
    private BigDecimal price;
    @ManyToMany(
            mappedBy = "lures"
    )
    private List<Fisher> fishers = new ArrayList();
    @ManyToMany(
            mappedBy = "lures"
    )
    private List<Fish> fishs = new ArrayList();

    public Lure() {
    }

    public Integer getCountHooks() {
        return this.countHooks;
    }

    public void setCountHooks(Integer numberHooks) {
        this.countHooks = numberHooks;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDivingDepth() {
        return this.divingDepth;
    }

    public void setDivingDepth(Double divignDepth) {
        this.divingDepth = divignDepth;
    }

    public TypeBait Imitation() {
        return this.typeBait;
    }

    public void setImitation(TypeBait typeBait) {
        this.typeBait = typeBait;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fisher> getFishers() {
        return this.fishers;
    }

    public void setFishers(List<Fisher> fishers) {
        this.fishers = fishers;
    }

    public List<Fish> getFishs() {
        return this.fishs;
    }

    public void setFishs(List<Fish> fishes) {
        this.fishs = fishes;
    }
}