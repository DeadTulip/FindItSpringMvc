package p.hh.smvc.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "location")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Location {
}
