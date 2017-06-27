package p.hh.smvc.dao;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import p.hh.smvc.domain.User;

import static org.junit.Assert.*;

public class UserDaoTest extends AbstractDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void findTest() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        assertNull(user.getId());

        userDao.create(user);
        assertNotNull(user.getId());

        User foundUser = userDao.findById(user.getId());
        assertNotNull(foundUser);
    }
}
