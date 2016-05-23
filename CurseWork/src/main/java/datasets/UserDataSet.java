package datasets;

import entitys.extend.LongId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserDataSet implements Serializable, LongId {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "pass", unique = false, updatable = false)
    private String pass;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet() {
    }

    public UserDataSet(String login, String pass) {
        setId(-1L);
        setLogin(login);
        setPass(pass);
    }

    public Long getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id + ", " +
                "login='" + login + "', " +
                "pass='" + pass + "'" +
                '}';
    }
}
