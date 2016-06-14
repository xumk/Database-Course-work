package modal.entity.joinentity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Embeddable
@Table(
        name = "peck"
)
public class Peck implements Serializable {
    @Id
    @Column(
            name = "fish_id"
    )
    private Long fishId;
    @Id
    @Column(
            name = "lure_id"
    )
    private Long lureId;

    public Peck() {
    }

    public Peck(Long fishId, Long lureId) {
        this.fishId = fishId;
        this.lureId = lureId;
    }

    public Long getFishId() {
        return this.fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public Long getLureId() {
        return this.lureId;
    }

    public void setLureId(Long lureId) {
        this.lureId = lureId;
    }
}
