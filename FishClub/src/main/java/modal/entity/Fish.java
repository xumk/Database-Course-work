package modal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
            name = "min_weight"
    )
    private Double minWeight;

    @Column(name = "max_weight")
    private Double maxWeight;


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

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Double minWeight) {
        this.minWeight = minWeight;
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
