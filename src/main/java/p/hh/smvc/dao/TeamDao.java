package p.hh.smvc.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.Item;
import p.hh.smvc.domain.Team;
import p.hh.smvc.domain.User;

import java.util.List;

@Repository("teamDao")
public class TeamDao extends AbstractHibernateDao<Team> {

    public List<Item> findAllSharedItemsByTeamId(Long teamId) {
        Query query = getSession().createQuery("from Item i where :team in elements(i.sharedTeams)");
        query.setParameter("team", findById(teamId));
        return query.list();
    }

}
