package modal.entity.joinentity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Embeddable
@Table(
        name = "lived"
)
public class Lived implements Serializable {
    @Id
    @Column(
            name = "fish_id"
    )
    private Long fishId;
    @Id
    @Column(
            name = "lake_id"
    )
    private Long lakeId;
    @Column(
            name = "count_fish"
    )
    private Integer countFish;

    public Lived() {
    }

    public Lived(Long fishId, Long lakeId) {
        this.fishId = fishId;
        this.lakeId = lakeId;
    }

    public Long getFishId() {
        return this.fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public Long getLakeId() {
        return this.lakeId;
    }

    public void setLakeId(Long lakeId) {
        this.lakeId = lakeId;
    }

    public Integer getCountFish() {
        return countFish;
    }

    public void setCountFish(Integer countFish) {
        this.countFish = countFish;
    }
}
