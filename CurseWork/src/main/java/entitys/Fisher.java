package entitys;

import entitys.agregations.Gender;
import entitys.extend.LongId;
import entitys.extend.Named;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Алексей on 30.04.2016.
 */
@Entity(name = "fisher")
@Table(name = "b_fisher")
public interface Fisher extends LongId, Named {

    /**
     * Имя рыбака
     */
    @Override
    @Column(name = "firstname")
    String getName();

    /**
     * Фамилия рыбака
     */
    @Column(name = "lastname")
    String getLastName();

    void setLastName(String lastName);

    /**
     * Отчество рыбака
     */
    @Column(name = "middlename")
    String getMiddleName();

    void setMiddleName(String middleName);

    /**
     * Пол рыбака
     */
    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    Gender getGender();

    void setGender(Gender gender);

    /**
     * Дата рождения рыбака
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    Date getBirthDay();

    void setBirthDay(Date date);
}
