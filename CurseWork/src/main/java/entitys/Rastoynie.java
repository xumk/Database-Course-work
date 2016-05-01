package entitys;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * сущность расстояние
 */
public interface Rastoynie extends LongIdRootBean {
    @ManyToMany
    @Column(name = "lough")
    List<Lough> getLoughs();

    @ManyToMany
    @Column(name = "fisher")
    List<Fisher> getFishers();

}
