package modal.entity.joinentity;

import modal.entity.Fish;
import modal.entity.Fisher;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "prefers")
@Table(name = "prefers")
public class Prefers implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "prefers_id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "fish_id")
    private Fish fish;

    @ManyToOne()
    @JoinColumn(name = "fisher_id")
    private Fisher fisher;

    public Prefers() {
    }

    public Prefers(Fish fish, Fisher fisher) {
        this.fish = fish;
        this.fisher = fisher;
    }

    public Fisher getFisher() {
        return this.fisher;
    }

    public void setFisher(Fisher fisher) {
        this.fisher = fisher;
    }

    public Fish getFish() {
        return this.fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Prefers prefers = (Prefers) obj;
        return fish.getId().equals(prefers.getFish().getId())
                && fisher.getId().equals(prefers.getFisher().getId());
    }
}
