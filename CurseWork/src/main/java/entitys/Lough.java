package entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * сущность озеро
 */
@Entity(name = "lough")
@Table(name = "b_lough")
public interface Lough extends LongIdRootBean, Named {

    /**
     * глубина озера
     */
    @Column(name = "depth")
    Double getDepth();
    void setDepth(Double depth);

    /**
     * занимаемая площадь озером
     */
    @Column(name = "area")
    Double getArea();
    void setArea(Double area);

}
