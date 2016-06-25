package modal.entity;

import modal.entity.joinentity.Distance;
import modal.entity.joinentity.Lived;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-сущность таблицы Озера, является простым
 * аннотированым классом с методами get(получить)
 * и set (изменить)
 */
@Entity(name = "lake")
@Table(name = "lakes")
public class Lake implements Serializable {

    /**
     * Идентификатор озера
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Название озера
     */
    @Column(name = "name")
    private String name;

    /**
     * Глубина озера, самая глубокая его точка
     */
    @Column(name = "depth")
    private Double depth;

    /**
     * Занимаемая озером площадь
     */
    @Column(name = "area")
    private Double area;

    /**
     * Связь сообщающая о том какие рыбаки и на каком
     * расстоянии от озера проживают
     */
    @OneToMany(mappedBy = "lake", cascade = CascadeType.ALL)
    private List<Distance> fishers = new ArrayList<>();

    /**
     * Связь сообщающая о том какие рыбы и
     * в каком количеств обитают в озере
     */
    @OneToMany(mappedBy = "lake", cascade = CascadeType.ALL)
    private List<Lived> fishs = new ArrayList<>();

    public Lake() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDepth() {
        return this.depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getArea() {
        return this.area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public List<Distance> getFishers() {
        return this.fishers;
    }

    public void setFishers(List<Distance> fishers) {
        this.fishers = fishers;
    }

    public List<Lived> getFishs() {
        return this.fishs;
    }

    public void setFishs(List<Lived> fishs) {
        this.fishs = fishs;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
