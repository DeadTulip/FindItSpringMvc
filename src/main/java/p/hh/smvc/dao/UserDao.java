package p.hh.smvc.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.Item;
import p.hh.smvc.domain.Team;
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

    public List<Team> findAllCreatedTeamsByUserId(long userId) {
        Query query = getSession().createQuery("from Team where creator.id = :userId");
        query.setParameter("userId", userId);
        return query.list();
    }

    public List<Team> findAllAccessibleTeamsByUserId(long userId) {
        Query query = getSession().createQuery(
                "from Team t where :user in elements(t.members)");
        query.setParameter("user", findById(userId));
        return query.list();
    }

    public User findByUsername(String username) {
        Query query = getSession().createQuery("from User where username = :username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }
}
