package modal.entity.joinentity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(
        name = "availability"
)
@Table(
        name = "availability"
)
@Embeddable
public class Availability implements Serializable {
    @Id
    @Column(
            name = "fisher_id"
    )
    private Long fisherId;
    @Id
    @Column(
            name = "lure_id"
    )
    private Long lureId;
    @Column(
            name = "count_lure"
    )
    private Integer countLure;

    public Availability() {
    }

    public Availability(Long fisherId, Long lureId) {
        this.fisherId = fisherId;
        this.lureId = lureId;
    }

    public Long getFisherId() {
        return this.fisherId;
    }

    public void setFisherId(Long fisherId) {
        this.fisherId = fisherId;
    }

    public Long getLureId() {
        return this.lureId;
    }

    public void setLureId(Long lureId) {
        this.lureId = lureId;
    }

    public Integer getCountLure() {
        return this.countLure;
    }

    public void setCountLure(Integer countLure) {
        this.countLure = countLure;
    }
}
