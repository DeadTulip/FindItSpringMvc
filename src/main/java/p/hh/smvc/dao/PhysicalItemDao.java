package p.hh.smvc.dao;

import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.PhysicalItem;

@Repository("physicalItemDao")
public class PhysicalItemDao extends AbstractHibernateDao<PhysicalItem> {
}
