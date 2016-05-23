package entitys.extend;

import javax.persistence.Column;

/**
 * Created by Алексей on 30.04.2016.
 */
public interface Named {

    /**
     * Имя
     */
    @Column(name = "name")
    String getName();

    void setName(String name);
}
