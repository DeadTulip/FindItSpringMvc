package p.hh.smvc.dao;

import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.DiskItem;

@Repository("diskItemDao")
public class DiskItemDao extends AbstractHibernateDao<DiskItem> {
}
