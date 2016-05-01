package entitys;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Сущность нажвки
 */
@Entity(name = "bait")
@Table(name = "b_bait")
public interface Bait extends LongIdRootBean, Named {

    /**
     * Количество крючков на наживке
     * может отсутсвовать
     */
    @Column(name = "numberhooks")
    Integer getNumberHooks();

    void setNumberHooks(Integer numberHooks);

    /**
     * Вес наживки
     */
    @Column(name = "weight")
    Double getWeight();

    void setWeight(Double weight);

    /**
     * Глубина погружения наживки
     */
    @Column(name = "divingdepth")
    Double getDivingDepth();

    void setDivingDepth(Double value);

    /**
     * Искусственная ли наживка
     */
    @Column(name = "isimitation")
    Boolean isImitation();
    void setImitation(Boolean value);

    /**
     * Цвет наживки
     */
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    Color getColor();

    void setColour(Color color);

    /**
     * Цена наживки
     */
    @Column(name = "price")
    BigDecimal getPrice();

    void setPrice(BigDecimal price);
}
