package p.hh.smvc.dao;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import p.hh.smvc.domain.Team;
import p.hh.smvc.domain.User;

import java.util.Set;

public class TeamDaoTest extends AbstractDaoTest {

    @Autowired
    TeamDao teamDao;

    @Autowired
    UserDao userDao;

    @Test
    public void findTest() {
        User user = new User();
        user.setUsername("tempUserName");
        user.setPassword("tempPassword");
        userDao.create(user);

        Team team = new Team();
        team.setTeamName("tempTeamName");
        team.setCreator(user);
        teamDao.create(team);

        user.getTeams().add(team);
        userDao.update(user);
        teamDao.update(team);

        System.out.println("userId = " + user.getId());
        System.out.println("teamId = " + team.getId());
        // ?????????????
//        Set teams = userDao.findById(user.getId()).getTeams();
//        Set users = teamDao.findById(team.getId()).getMembers();
//        System.out.println(teams);
//        System.out.println(users);
    }
}
