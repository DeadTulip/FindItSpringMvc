package p.hh.smvc.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -3512521164157856626L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @ManyToMany
    @JoinTable(
            name = "team_user",
            joinColumns = { @JoinColumn(name = "user_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "team_id", nullable = false) }
    )
    private final Set<Team> teams = new HashSet<>();

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Team> getTeams() {
        return teams;
    }
}
