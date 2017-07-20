package p.hh.smvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import p.hh.smvc.domain.Item;
import p.hh.smvc.domain.Team;
import p.hh.smvc.domain.User;
import p.hh.smvc.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userId}")
    public User getUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return user;
    }

    @RequestMapping(value = "/{userId}/createdItems")
    public List<Item> createdItems(@PathVariable Long userId) {
        List<Item> items = userService.findAllCreatedItems(userId);
        return items;
    }

    @RequestMapping(value = "/{userId}/accessibleItems")
    public List<Item> accessibleItems(@PathVariable Long userId) {
        List<Item> items = userService.findAllAccessibleItems(userId);
        return items;
    }

    @RequestMapping(value = "/{userId}/createdTeams")
    public List<Team> createdTeams(@PathVariable Long userId) {
        List<Team> teams = userService.findAllCreatedTeams(userId);
        return teams;
    }

    @RequestMapping(value = "/{userId}/accessibleTeams")
    public List<Team> accessibleTeams(@PathVariable Long userId) {
        List<Team> teams = userService.findAllAccessibleTeams(userId);
        return teams;
    }
}
