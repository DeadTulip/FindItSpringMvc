package p.hh.smvc.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {

    @Column(name = "teamname")
    private String teamName;

    @OneToOne
    @JoinColumn(name = "creator")
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "team_user",
            joinColumns = { @JoinColumn(name = "team_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) }
    )
    private final Set<User> members = new HashSet<>();

    @ManyToMany
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
