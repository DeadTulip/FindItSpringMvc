package p.hh.smvc.dao;

import org.springframework.stereotype.Repository;
import p.hh.smvc.domain.User;

@Repository("userDao")
public class UserDao extends AbstractHibernateDao<User> {
}
