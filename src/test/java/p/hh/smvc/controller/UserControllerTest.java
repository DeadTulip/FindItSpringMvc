package p.hh.smvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import p.hh.smvc.conf.AppConfiguration;
import p.hh.smvc.conf.AppTestConfiguration;
import p.hh.smvc.conf.HibernateTestConfiguration;
import p.hh.smvc.dao.UserDao;
import p.hh.smvc.domain.User;
import p.hh.smvc.services.UserService;

import javax.servlet.ServletContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UserControllerTest extends AbstractControllerTest {

    @Autowired
    UserService userService;

    @Test
    public void testGetUser() throws Exception {
        User user = new User();
        user.setUsername("myName");
        user.setPassword("myPassword");
        Long userId = userService.createUser(user);

        this.mockMvc.perform(get("/user/" + userId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andReturn();
    }
}
