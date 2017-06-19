package p.hh.smvc.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("P")
public class PhysicalItem extends Item {

    @OneToOne
    @JoinColumn(name = "picture")
    private DiskLocation picture;

    public PhysicalItem() {
    }

    public DiskLocation getPicture() {
        return picture;
    }

    public void setPicture(DiskLocation picture) {
        this.picture = picture;
    }
}
