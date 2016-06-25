package modal.entity;

import modal.entity.agregation.Gender;
import modal.entity.joinentity.Availability;
import modal.entity.joinentity.Distance;
import modal.entity.joinentity.Prefers;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс-сущность таблицы Рыбаки, является простым
 * аннотированым классом с методами get(получить)
 * и set (изменить)
 */
@Entity(name = "fisher")
@Table(name = "fishers")
public class Fisher implements Serializable {

    /**
     * Идентификатор рыбака
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Имя рыбака
     */
    @Column(name = "first_name")
    private String name;

    /**
     * Фамилия рыбака
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество рыбака (может отсутсвовать)
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Пол рыбака
     */
    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    /**
     * Дата рождения рыбака
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthDay;

    /**
     * Список связей, сообщающий на каком расстоянии от какого озера
     * проживает данный рыбак
     */
    @OneToMany(mappedBy = "fisher", cascade = {CascadeType.ALL})
    private List<Distance> lakes = new ArrayList<>();

    /**
     * Список связей, сообщающий какую наживку и в каком количестве
     * имеет рыбак
     */
    @OneToMany(mappedBy = "fisher", cascade = {CascadeType.ALL})
    private List<Availability> lures = new ArrayList<>();

    /**
     * Список связей сообщающий какую рыбу предпочитает рыбак
     */
    @OneToMany(mappedBy = "fisher", cascade = {CascadeType.ALL})
    private List<Prefers> fishs = new ArrayList<>();

    public Fisher() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public List<Prefers> getFishs() {
        return this.fishs;
    }

    public void setFishs(List<Prefers> fishs) {
        this.fishs = fishs;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(Date date) {
        this.birthDay = date;
    }

    public List<Distance> getLakes() {
        return this.lakes;
    }

    public void setLakes(List<Distance> lakes) {
        this.lakes = lakes;
    }

    public List<Availability> getLures() {
        return this.lures;
    }

    public void setLures(List<Availability> lures) {
        this.lures = lures;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(name).append(", ");
        sb.append(middleName).append(", ");
        sb.append(lastName).append(", ");
        sb.append(birthDay.toString()).append("]");
        return sb.toString();
    }
}
