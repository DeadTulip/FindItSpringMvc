package p.hh.smvc.commands;

import p.hh.smvc.domain.DiskLocation;
import p.hh.smvc.domain.Location;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ItemCommand {
    private ItemType itemType;
    private Long ownerId;
    private String ownerName;
    private Long itemId;
    private String itemName;
    private Long fileSize;
    private String fileType;
    private Date eventStart;
    private Date eventEnd;
    private List<String> selectedPeople = new ArrayList<>();
    private String additionalPeople;
    private List<String> selectedPlaces = new ArrayList<>();
    private String additionalPlaces;
    private String description;
    private List<String> sharedTeams = new ArrayList<>();
    private Location itemLocation;
    private DiskLocation pictureLocation;

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public List<String> getSelectedPeople() {
        return selectedPeople;
    }

    public String getAdditionalPeople() {
        return additionalPeople;
    }

    public void setAdditionalPeople(String additionalPeople) {
        this.additionalPeople = additionalPeople;
    }

    public List<String> getSelectedPlaces() {
        return selectedPlaces;
    }

    public String getAdditionalPlaces() {
        return additionalPlaces;
    }

    public void setAdditionalPlaces(String additionalPlaces) {
        this.additionalPlaces = additionalPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSharedTeams() {
        return sharedTeams;
    }

    public Location getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(Location itemLocation) {
        this.itemLocation = itemLocation;
    }

    public DiskLocation getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(DiskLocation pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    public String getInvolvedPeople() {
        return getInvolvedInfo(selectedPeople, additionalPeople);
    }

    public String getInvolvedPlaces() {
        return getInvolvedInfo(selectedPlaces, additionalPlaces);
    }

    public void setSelectedPeople(List<String> selectedPeople) {
        this.selectedPeople = selectedPeople;
    }

    public void setSelectedPlaces(List<String> selectedPlaces) {
        this.selectedPlaces = selectedPlaces;
    }

    public void setSharedTeams(List<String> sharedTeams) {
        this.sharedTeams = sharedTeams;
    }

    private String getInvolvedInfo(List<String> selectedInfo, String additionalInfo) {
        List<String> additionalInfoList = new ArrayList<>();
        if(additionalInfo != null) {
            for (String info : additionalInfo.split(",")) {
                additionalInfoList.add(info.trim());
            }
        }
        selectedInfo.addAll(additionalInfoList);
        List<String> allInfoList = selectedInfo.stream().distinct().collect(Collectors.toList());
        return String.join(",", allInfoList);
    }
}
