package entitys;

import entitys.extend.LongId;
import entitys.extend.Named;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Интерфейс описывающий рыбу
 */
@Entity(name = "fish")
@Table(name = "b_fish")
public interface Fish extends LongId, Named {
    /**
     * семейство рыбы (пока строкой)
     */
    @Column(name = "family")
    String getFamily();

    void setFamily(String family);

    /**
     * минимальный вес рыбы
     */
    @Column(name = "minweight")
    Double getMinWeight();

    void setMinWeight(Double minWeight);

    /**
     * максимальный вес рыбы
     */
    @Column(name = "maxweight")
    Double getMaxWeight();

    void setMaxWeight(Double maxWeight);

    /**
     * глубина обитания рыбы
     */
    @Column(name = "depthdwelling")
    Double getDepthDwelling();

    void setDepthDwelling(Double depthDwelling);

    @ManyToMany
    @Column(name = "rastor")
    List<Rastoynie> getRastoynie();
}
