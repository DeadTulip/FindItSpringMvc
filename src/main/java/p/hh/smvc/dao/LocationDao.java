package p.hh.smvc.dao;

import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.Location;

@Repository("locationDao")
public class LocationDao extends AbstractHibernateDao<Location> {
}
