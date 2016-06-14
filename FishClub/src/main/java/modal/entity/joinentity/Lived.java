package modal.entity.joinentity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

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
    private Long countFish;

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
}
