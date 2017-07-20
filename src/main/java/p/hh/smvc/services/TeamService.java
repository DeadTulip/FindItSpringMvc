package p.hh.smvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.hh.smvc.commands.TeamCommand;
import p.hh.smvc.dao.TeamDao;
import p.hh.smvc.domain.Item;
import p.hh.smvc.domain.Team;
import p.hh.smvc.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class TeamService {

    @Autowired
    private TeamDao teamDao;

    public Team findById(Long id) {
        return teamDao.findById(id);
    }

    public List<User> findAllMembers(Long teamId) {
        return new ArrayList<>(teamDao.findById(teamId).getMembers());
    }

    public List<Item> findAllSharedItems(Long teamId) {
        return teamDao.findAllSharedItemsByTeamId(teamId);
    }
}
