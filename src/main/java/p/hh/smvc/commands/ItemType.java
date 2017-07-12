package p.hh.smvc.commands;

public enum ItemType {
    DISK, PHYSICAL;

    static ItemType getValue(String value) {
        if(value.equalsIgnoreCase("disk")) {
            return DISK;
        } else {
            return PHYSICAL;
        }
    }

}
