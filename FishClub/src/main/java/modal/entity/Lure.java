package modal.entity;

import modal.entity.joinentity.Availability;
import modal.entity.joinentity.Peck;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "lure")
@Table(name = "lures")
public class Lure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_hooks")
    private Integer countHooks;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "diving_depth")
    private Double divingDepth;

    @Column(name = "is_imitation")
    private Boolean imitation;

    @OneToMany(mappedBy = "lure", cascade = CascadeType.ALL)
    private List<Availability> fishers = new ArrayList();

    @OneToMany(mappedBy = "lure", cascade = CascadeType.ALL)
    private List<Peck> fishs = new ArrayList();

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

    public Boolean isImitation() {
        return this.imitation;
    }

    public void setImitation(Boolean isImitation) {
        this.imitation = isImitation;
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

    public List<Availability> getFishers() {
        return this.fishers;
    }

    public void setFishers(List<Availability> fishers) {
        this.fishers = fishers;
    }

    public List<Peck> getFishs() {
        return this.fishs;
    }

    public void setFishs(List<Peck> fishes) {
        this.fishs = fishes;
    }

    @Override
    public String toString() {
        return name;
    }
}