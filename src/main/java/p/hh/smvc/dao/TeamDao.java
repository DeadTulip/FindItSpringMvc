package p.hh.smvc.dao;

import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.Team;

@Repository("teamDao")
public class TeamDao extends AbstractHibernateDao<Team> {
}
