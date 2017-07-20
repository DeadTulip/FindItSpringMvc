package p.hh.smvc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team extends BaseEntity {

    @Column(name = "teamname")
    private String teamName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private User creator;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_user",
            joinColumns = { @JoinColumn(name = "team_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) }
    )
    private final Set<User> members = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "item_team",
            joinColumns = { @JoinColumn(name = "team_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "item_id", nullable = false) }
    )
    private final Set<Item> items = new HashSet<>();

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getMembers() {
        return members;
    }

    public Set<Item> getItems() {
        return items;
    }
}
