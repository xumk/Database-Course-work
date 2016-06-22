package modal.entity.joinentity;

import modal.entity.Fisher;
import modal.entity.Lake;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "distance")
@Table(name = "distance")
public class Distance implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "distance_id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "fisher_id")
    private Fisher fisher;

    @ManyToOne()
    @JoinColumn(name = "lake_id")
    private Lake lake;

    @Column(name = "distance")
    private Double distance;

    public Distance(Fisher fisher, Lake lake, Double distance) {
        this.fisher = fisher;
        this.lake = lake;
        this.distance = distance;
    }

    public Distance() {
    }

    public Fisher getFisher() {
        return this.fisher;
    }

    public void setFisher(Fisher fisher) {
        this.fisher = fisher;
    }

    public Lake getLake() {
        return this.lake;
    }

    public void setLake(Lake lake) {
        this.lake = lake;
    }

    public Double getDistance() {
        return this.distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Distance prefers = (Distance) obj;
        return lake.getId().equals(prefers.getLake().getId())
                && fisher.getId().equals(prefers.getFisher().getId());
    }
}

