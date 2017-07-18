package p.hh.smvc.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.Item;
import p.hh.smvc.domain.User;

import java.util.List;
import java.util.Set;

@Repository("userDao")
public class UserDao extends AbstractHibernateDao<User> {

    public List<Item> findAllCreatedItemsByUserId(Long userId) {
        Query query = getSession().createQuery("from Item where owner_id = :userId");
        query.setParameter("userId", userId);
        return query.list();
    }
}
