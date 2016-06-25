package modal.entity.joinentity;

import modal.entity.Fish;
import modal.entity.Lake;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность таблицы Обитает, является простым
 * аннотированым классом с методами get(получить)
 * и set (изменить)
 */
@Entity(name = "lived")
@Table(name = "lived")
public class Lived implements Serializable {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    @Column(name = "lived_id")
    private long id;

    /**
     * Рыба
     */
    @ManyToOne()
    @JoinColumn(name = "fish_id")
    private Fish fish;

    /**
     * Озеро
     */
    @ManyToOne()
    @JoinColumn(name = "lake_id")
    private Lake lake;

    /**
     * Популяция особей
     */
    @Column(name = "count_fish")
    private Integer countFish;

    public Lived() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lived(Fish fish, Lake lake) {
        this.fish = fish;
        this.lake = lake;
    }

    public Lived(Fish fish, Lake lake, Integer count) {
        this.fish = fish;
        this.lake = lake;
        countFish = count;
    }

    public Fish getFish() {
        return this.fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public Lake getLake() {
        return this.lake;
    }

    public void setLake(Lake lake) {
        this.lake = lake;
    }

    public Integer getCountFish() {
        return countFish;
    }

    public void setCountFish(Integer countFish) {
        this.countFish = countFish;
    }

    @Override
    public boolean equals(Object obj) {
        Lived lived = (Lived) obj;
        return fish.getId().equals(lived.getFish().getId())
                && lake.getId().equals(lived.getLake().getId());
    }
}
