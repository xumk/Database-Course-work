package modal.entity;

import modal.entity.joinentity.Lived;
import modal.entity.joinentity.Peck;
import modal.entity.joinentity.Prefers;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "fish")
@Table(name = "fishs")
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "family")
    private String family;

    @Column(name = "min_weight")
    private Double minWeight;

    @Column(name = "max_weight")
    private Double maxWeight;


    @Column(name = "depth_living")
    private Double depthLiving;

    @OneToMany(mappedBy = "fish", cascade = CascadeType.ALL)
    private List<Peck> lures = new ArrayList<>();

    @OneToMany(mappedBy = "fish", cascade = CascadeType.ALL)
    private List<Prefers> fishers = new ArrayList<>();

    @OneToMany(mappedBy = "fish", cascade = CascadeType.ALL)
    private List<Lived> lakes = new ArrayList<>();

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

    public List<Peck> getLures() {
        return this.lures;
    }

    public List<Prefers> getFishers() {
        return this.fishers;
    }

    public void setFishers(List<Prefers> fishers) {
        this.fishers = fishers;
    }

    public List<Lived> getLakes() {
        return this.lakes;
    }

    public void setLakes(List<Lived> lakes) {
        this.lakes = lakes;
    }

    public void setLures(List<Peck> lures) {
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

    @Override
    public String toString() {
        return name;
    }
}
