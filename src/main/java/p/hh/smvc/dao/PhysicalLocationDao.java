package p.hh.smvc.dao;

import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.PhysicalLocation;

@Repository("physicalLocationDao")
public class PhysicalLocationDao extends AbstractHibernateDao<PhysicalLocation> {
}
