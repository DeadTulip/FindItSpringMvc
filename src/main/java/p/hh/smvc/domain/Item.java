package p.hh.smvc.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import p.hh.smvc.commands.BaseEntitySerializer;
import p.hh.smvc.commands.ItemType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DiskItem.class, name = "disk"),
        @JsonSubTypes.Type(value = PhysicalItem.class, name = "physical")
})
public abstract class Item extends BaseEntity {

    @JsonSerialize(using = BaseEntitySerializer.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "name")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "date_created")
    private Date dateCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "date_updated")
    private Date dateUpdated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "event_start_time")
    private Date eventStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "event_end_time")
    private Date eventEndTime;

    @Column(name = "people")
    private String involvedPeople;

    @Column(name = "places")
    private String involvedPlaces;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_item",
            joinColumns = { @JoinColumn(name = "item_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "team_id", nullable = false) }
    )
    private final Set<Team> sharedTeams = new HashSet<>();

    public Item() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Date eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Date getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(Date eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getInvolvedPeople() {
        return involvedPeople;
    }

    public void setInvolvedPeople(String involvedPeople) {
        this.involvedPeople = involvedPeople;
    }

    public String getInvolvedPlaces() {
        return involvedPlaces;
    }

    public void setInvolvedPlaces(String involvedPlaces) {
        this.involvedPlaces = involvedPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Team> getSharedTeams() {
        return sharedTeams;
    }
}
