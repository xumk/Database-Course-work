package modal.entity.joinentity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Embeddable
@Table(
        name = "Prefers"
)
public class Prefers implements Serializable {
    @Id
    @Column(
            name = "fish_id"
    )
    private Long fishId;
    @Id
    @Column(
            name = "fisher_id"
    )
    private Long fisherId;

    public Prefers() {
    }

    public Prefers(Long fishId, Long fisherId) {
        this.fishId = fishId;
        this.fisherId = fisherId;
    }

    public Long getFisherId() {
        return this.fisherId;
    }

    public void setFisherId(Long fisherId) {
        this.fisherId = fisherId;
    }

    public Long getFishId() {
        return this.fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }
}
