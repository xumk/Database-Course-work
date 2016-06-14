package modal.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import modal.entity.Fisher;
import modal.entity.Lake;
import modal.entity.Lure;
import modal.entity.agregation.WeigthCategory;

@Entity(
        name = "fish"
)
@Table(
        name = "fish"
)
public class Fish {
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
            name = "family"
    )
    private String family;
    @Column(
            name = "weigth_category"
    )
    @Enumerated(EnumType.ORDINAL)
    private WeigthCategory weigthCategory;
    @Column(
            name = "depth_living"
    )
    private Double depthLiving;
    @ManyToMany(
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "peck",
            joinColumns = {            @JoinColumn(
                    name = "fish_id"
            )},
            inverseJoinColumns = {            @JoinColumn(
                    name = "lure_id"
            )}
    )
    private List<Lure> lures = new ArrayList();
    @ManyToMany(
            mappedBy = "fishs"
    )
    private List<Fisher> fishers = new ArrayList();
    @ManyToMany(
            mappedBy = "fishs"
    )
    private List<Lake> lakes = new ArrayList();

    public Fish() {
    }

    public String getFamily() {
        return this.family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public WeigthCategory getWeigthCategory() {
        return this.weigthCategory;
    }

    public void setWeigthCategory(WeigthCategory weigthCategory) {
        this.weigthCategory = weigthCategory;
    }

    public Double getDepthLiving() {
        return this.depthLiving;
    }

    public void setDepthLiving(Double depthLiving) {
        this.depthLiving = depthLiving;
    }

    public List<Lure> getLures() {
        return this.lures;
    }

    public List<Fisher> getFishers() {
        return this.fishers;
    }

    public void setFishers(List<Fisher> fishers) {
        this.fishers = fishers;
    }

    public List<Lake> getLakes() {
        return this.lakes;
    }

    public void setLakes(List<Lake> lakes) {
        this.lakes = lakes;
    }

    public void setLures(List<Lure> lures) {
        this.lures = lures;
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
}
