package modal.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс-сущность таблицы Пользователи, является простым
 * аннотированым классом с методами get(получить) и set (изменить)
 */
@Entity(name = "user")
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Логин пользователя
     */
    @Column(name = "login", unique = true)
    private String login;

    /**
     * Пароль пользователя
     */
    @Column(name = "password", unique = false)
    private String password;

    /**
     * Рыбак связанный с этим пользователем
     */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(
            name = "fk_fisherman_id",
            referencedColumnName = "id"
    )
    private Fisher fisher;

    /**
     * Роль пользователя в системе
     */
    @Column(name = "isAdmin")
    private Boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getLogin() {
        return this.login;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public Fisher getFisher() {
        return this.fisher;
    }

    public void setFisher(Fisher fisher) {
        this.fisher = fisher;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(login).append(", ");
        sb.append(fisher.toString()).append("]");
        return sb.toString();
    }
}
