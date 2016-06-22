package modal.entity.joinentity;

import modal.entity.Fisher;
import modal.entity.Lure;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "availability")
@Table(name = "availability")
public class Availability implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "availability_id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "fisher_id")
    private Fisher fisher;

    @ManyToOne()
    @JoinColumn(name = "lure_id")
    private Lure lure;

    @Column(name = "count_lure")
    private Integer countLure;

    public Availability() {
    }

    public Availability(Fisher fisher, Lure lure, Integer countLure) {
        this.fisher = fisher;
        this.lure = lure;
        this.countLure = countLure;
    }

    public Fisher getFisher() {
        return this.fisher;
    }

    public void setFisher(Fisher fisher) {
        this.fisher = fisher;
    }

    public Lure getLure() {
        return this.lure;
    }

    public void setLure(Lure lure) {
        this.lure = lure;
    }

    public Integer getCountLure() {
        return this.countLure;
    }

    public void setCountLure(Integer countLure) {
        this.countLure = countLure;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Availability prefers = (Availability) obj;
        return lure.getId().equals(prefers.getLure().getId())
                && fisher.getId().equals(prefers.getFisher().getId());
    }
}
