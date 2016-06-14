package modal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "users"
)
public class User implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;
    @Id
    @Column(
            name = "id"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "login",
            unique = true
    )
    private String login;
    @Column(
            name = "password",
            unique = false
    )
    private String password;
    @OneToOne(
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "fk_fisherman_id",
            referencedColumnName = "id"
    )
    private Fisher fisherman;

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

    public Fisher getFisherman() {
        return this.fisherman;
    }

    public void setFisherman(Fisher fisherman) {
        this.fisherman = fisherman;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(login).append(", ");
        sb.append(fisherman.toString()).append("]");
        return sb.toString();
    }
}
