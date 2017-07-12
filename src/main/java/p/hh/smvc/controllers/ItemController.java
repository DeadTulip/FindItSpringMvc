package p.hh.smvc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import p.hh.smvc.commands.ItemCommand;
import p.hh.smvc.domain.Item;
import p.hh.smvc.services.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String sayHello(ModelMap model) {
        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        return "welcome";
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ItemCommand open(@PathVariable Long itemId) {
        Item item = itemService.findById(itemId);
        ItemCommand cmd = itemService.mapItemToCommand(item);
        return cmd;
    }
}
