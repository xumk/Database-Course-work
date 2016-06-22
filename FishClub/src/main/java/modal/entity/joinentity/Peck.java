package modal.entity.joinentity;

import modal.entity.Fish;
import modal.entity.Lure;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "peck")
@Table(name = "peck")
public class Peck implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "peck_id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "fish_id")
    private Fish fish;

    @ManyToOne()
    @JoinColumn(name = "lure_id")
    private Lure lure;

    public Peck() {
    }

    public Peck(Fish fishId, Lure lureId) {
        this.fish = fishId;
        this.lure = lureId;
    }

    public Fish getFish() {
        return this.fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Lure getLure() {
        return this.lure;
    }

    public void setLure(Lure lure) {
        this.lure = lure;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Peck peck = (Peck) obj;
        return fish.getId().equals(peck.getFish().getId())
                && lure.getId().equals(peck.getLure().getId());
    }
}
