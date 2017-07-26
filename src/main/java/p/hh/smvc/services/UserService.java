package p.hh.smvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.hh.smvc.dao.UserDao;
import p.hh.smvc.domain.Item;
import p.hh.smvc.domain.Team;
import p.hh.smvc.domain.User;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TeamService teamService;

    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    public List<Item> findAllCreatedItems(Long userId) {
        return userDao.findAllCreatedItemsByUserId(userId);
    }

    public List<Team> findAllCreatedTeams(Long userId) {
        return userDao.findAllCreatedTeamsByUserId(userId);
    }

    public List<Item> findAllAccessibleItems(Long userId) {
        List<Team> accessibleTeams = findAllAccessibleTeams(userId);
        List<Item> accessibleItems = accessibleTeams.stream()
                .flatMap(team -> teamService.findAllSharedItems(team.getId()).stream())
                .distinct()
                .sorted(Comparator.comparing(Item::getId))
                .collect(Collectors.toList());
        return accessibleItems;
    }

    public List<Team> findAllAccessibleTeams(Long userId) {
        return userDao.findAllAccessibleTeamsByUserId(userId);
    }

    public Long createUser(User user) {
        userDao.create(user);
        return user.getId();
    }

}
