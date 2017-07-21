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

import javax.servlet.ServletContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WelcomeControllerTest extends AbstractControllerTest {

    @Test
    public void textBeanExistence() {
        ServletContext servletContext = wac.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("welcomeController"));
    }

    @Test
    public void testResponse() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        assertEquals("application/json;charset=UTF-8",
                mvcResult.getResponse().getContentType());
        assertEquals("\"Welcome!\"",
                mvcResult.getResponse().getContentAsString());
    }
}
