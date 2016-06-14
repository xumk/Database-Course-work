package modal.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import modal.entity.Fish;
import modal.entity.Fisher;

@Entity(
        name = "lake"
)
@Table(
        name = "lake"
)
public class Lake {
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
            name = "depth"
    )
    private Double depth;
    @Column(
            name = "area"
    )
    private Double area;
    @ManyToMany(
            mappedBy = "lakes"
    )
    private List<Fisher> fishers = new ArrayList();
    @ManyToMany(
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "lived",
            joinColumns = {            @JoinColumn(
                    name = "lake_id"
            )},
            inverseJoinColumns = {            @JoinColumn(
                    name = "fish_id"
            )}
    )
    private List<Fish> fishs = new ArrayList();

    public Lake() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDepth() {
        return this.depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getArea() {
        return this.area;
    }

    public void setArea(Double area) {
        this.area = area;
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

    public void setFishs(List<Fish> fishs) {
        this.fishs = fishs;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFisher(Fisher fisher) {
        this.fishers.add(fisher);
    }
}
