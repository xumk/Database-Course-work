package modal.entity;

import modal.entity.joinentity.Availability;
import modal.entity.joinentity.Peck;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-сущность таблицы Наживки, является простым
 * аннотированым классом с методами get(получить)
 * и set (изменить)
 */
@Entity(name = "lure")
@Table(name = "lures")
public class Lure implements Serializable {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Название наживки
     */
    @Column(name = "name")
    private String name;

    /**
     * Количество крючков у наживки
     */
    @Column(name = "number_hooks")
    private Integer countHooks;

    /**
     * Вес наживки
     */
    @Column(name = "weight")
    private Double weight;

    /**
     * Глубина погружения
     */
    @Column(name = "diving_depth")
    private Double divingDepth;

    /**
     * Является ли наживка искусственно?
     */
    @Column(name = "is_imitation")
    private Boolean imitation;

    /**
     * Связь сообщающая о том какие рыбаки предпочитают эту наживку
     */
    @OneToMany(mappedBy = "lure", cascade = CascadeType.ALL)
    private List<Availability> fishers = new ArrayList();

    /**
     * Связь сообщающая о том какие рыбы клюет на эту наживку
     */
    @OneToMany(mappedBy = "lure", cascade = CascadeType.ALL)
    private List<Peck> fishs = new ArrayList();

    public Lure() {
    }

    public Integer getCountHooks() {
        return this.countHooks;
    }

    public void setCountHooks(Integer numberHooks) {
        this.countHooks = numberHooks;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDivingDepth() {
        return this.divingDepth;
    }

    public void setDivingDepth(Double divignDepth) {
        this.divingDepth = divignDepth;
    }

    public Boolean isImitation() {
        return this.imitation;
    }

    public void setImitation(Boolean isImitation) {
        this.imitation = isImitation;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Availability> getFishers() {
        return this.fishers;
    }

    public void setFishers(List<Availability> fishers) {
        this.fishers = fishers;
    }

    public List<Peck> getFishs() {
        return this.fishs;
    }

    public void setFishs(List<Peck> fishes) {
        this.fishs = fishes;
    }

    @Override
    public String toString() {
        return name;
    }
}