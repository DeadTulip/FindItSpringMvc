package p.hh.smvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import p.hh.smvc.domain.Item;
import p.hh.smvc.domain.Team;
import p.hh.smvc.domain.User;
import p.hh.smvc.services.TeamService;

import java.util.List;

@RestController
@RequestMapping(value = "/team", method = RequestMethod.GET, produces = "application/json")
public class TeamController {

    @Autowired
    TeamService teamService;

    @RequestMapping(value = "/{teamId}")
    public Team getTeam(@PathVariable Long teamId) {
        Team team = teamService.findById(teamId);
        return team;
    }

    @RequestMapping(value = "/{teamId}/sharedItems")
    public List<Item> getSharedtems(@PathVariable Long teamId) {
        List<Item> items = teamService.findAllSharedItems(teamId);
        return items;
    }

    @RequestMapping(value = "/{teamId}/members")
    public List<User> getMembers(@PathVariable Long teamId) {
        List<User> users = teamService.findAllMembers(teamId);
        return users;
    }
}
