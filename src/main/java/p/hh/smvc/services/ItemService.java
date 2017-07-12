package p.hh.smvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.hh.smvc.commands.ItemCommand;
import p.hh.smvc.commands.ItemType;
import p.hh.smvc.dao.ItemDao;
import p.hh.smvc.dao.UserDao;
import p.hh.smvc.domain.DiskItem;
import p.hh.smvc.domain.Item;
import p.hh.smvc.domain.PhysicalItem;
import p.hh.smvc.domain.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Transactional
@Service
public class ItemService {

    @Autowired
    ItemDao itemDao;

    @Autowired
    UserDao userDao;

    Item createItem(ItemCommand command) {
        Item item = mapCommandToItem(command);
        Date now = new Date();
        item.setDateCreated(now);
        item.setDateUpdated(now);
        itemDao.create(item);

        return item;
    }

    public Item findById(Long id) {
        return itemDao.findById(id);
    }

    public ItemCommand mapItemToCommand(Item item) {
        ItemCommand cmd = new ItemCommand();
        cmd.setItemId(item.getId());
        cmd.setItemName(item.getName());
        String involvedPeople = item.getInvolvedPeople();
        cmd.setSelectedPeople(involvedPeople != null ? Arrays.asList(involvedPeople.split(",")) : new ArrayList<String>());
        String involvedPlaces = item.getInvolvedPlaces();
        cmd.setSelectedPlaces(involvedPlaces != null ? Arrays.asList(involvedPlaces.split(",")) : new ArrayList<String>());
        cmd.setDescription(item.getDescription());
        cmd.setEventStart(item.getEventStartTime());
        cmd.setEventEnd(item.getEventEndTime());

        cmd.getSharedTeams().addAll(
                findById(item.getId()).getSharedTeams().stream()
                        .map(Team::getTeamName)
                        .collect(Collectors.toList()));

        if (item instanceof DiskItem) {
            DiskItem diskItem = (DiskItem) item;
            cmd.setItemType(ItemType.DISK);
            cmd.setFileSize(diskItem.getFileSize());
            cmd.setFileType(diskItem.getFileType());

        } else {
            PhysicalItem physicalItem = (PhysicalItem) item;
            cmd.setItemType(ItemType.PHYSICAL);
            cmd.setPictureLocation(physicalItem.getPicture());
        }

        return cmd;
    }

    Item mapCommandToItem(ItemCommand command) {
        Item item = null;
        if(command.getItemType() == ItemType.DISK) {
            item = mapCommandToDiskItem(command);
        }
        return item;
    }

    private DiskItem mapCommandToDiskItem(ItemCommand command) {
        DiskItem item;
        if (command.getItemId() != null) {
            item = (DiskItem) itemDao.findById(command.getItemId());
        } else {
            item = new DiskItem();
        }
        bindItemData(command, item);

        item.setFileSize(command.getFileSize());
        item.setFileType(command.getFileType());
        return item;
    }

    private void bindItemData(ItemCommand command, Item item) {
        item.setOwner(userDao.findById(command.getOwnerId()));
        item.setLocation(command.getItemLocation());
        item.setName(command.getItemName());
        item.setInvolvedPeople(command.getInvolvedPeople());
        item.setInvolvedPlaces(command.getInvolvedPlaces());
        item.setDescription(command.getDescription());
        item.setEventStartTime(command.getEventStart());
        item.setEventEndTime(command.getEventEnd());
    }
}
