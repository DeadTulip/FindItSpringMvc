package p.hh.smvc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
