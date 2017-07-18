package p.hh.smvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import p.hh.smvc.commands.ItemCommand;
import p.hh.smvc.domain.Item;
import p.hh.smvc.services.ItemService;

@RestController
@RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json")
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/{itemId}/detail")
    public ItemCommand getDetail(@PathVariable Long itemId) {
        Item item = itemService.findById(itemId);
        ItemCommand cmd = itemService.mapItemToCommand(item);
        return cmd;
    }

    @RequestMapping(value = "/{itemId}")
    public Item getItem(@PathVariable Long itemId) {
        Item item = itemService.findById(itemId);
        return item;
    }
}
