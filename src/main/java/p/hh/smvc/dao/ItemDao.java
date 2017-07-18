package p.hh.smvc.dao;

import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.Item;

import java.util.Set;

@Repository("itemDao")
public class ItemDao extends AbstractHibernateDao<Item> {

}
