package modal.entity.joinentity;

import modal.entity.Fish;
import modal.entity.Fisher;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность таблицы Предпочитает, является простым
 * аннотированым классом с методами get(получить)
 * и set (изменить)
 */
@Entity(name = "prefers")
@Table(name = "prefers")
public class Prefers implements Serializable {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    @Column(name = "prefers_id")
    private long id;

    /**
     * Рыба
     */
    @ManyToOne()
    @JoinColumn(name = "fish_id")
    private Fish fish;

    /**
     * Рыбак
     */
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
