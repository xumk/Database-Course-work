package modal.entity.joinentity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(
        name = "distance"
)
@Table(
        name = "distance"
)
@Embeddable
public class Distance implements Serializable {
    @Id
    @Column(
            name = "fisher_id"
    )
    private Long fisherId;
    @Id
    @Column(
            name = "lake_id"
    )
    private Long lakeId;
    @Column(
            name = "distance"
    )
    private Double distance;

    public Distance(Long fisherId, Long lakeId) {
        this.fisherId = fisherId;
        this.lakeId = lakeId;
    }

    public Distance() {
    }

    public Long getFisherId() {
        return this.fisherId;
    }

    public void setFisherId(Long fisherId) {
        this.fisherId = fisherId;
    }

    public Long getLakeId() {
        return this.lakeId;
    }

    public void setLakeId(Long lakeId) {
        this.lakeId = lakeId;
    }

    public Double getDistance() {
        return this.distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}

