package p.hh.smvc.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.DiskLocation;

import java.util.List;

@Repository("diskLocationDao")
public class DiskLocationDao extends AbstractHibernateDao<DiskLocation> {

}
