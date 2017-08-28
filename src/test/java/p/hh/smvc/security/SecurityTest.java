package p.hh.smvc.security;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import p.hh.smvc.conf.AppConfiguration;
import p.hh.smvc.conf.HibernateTestConfiguration;
import p.hh.smvc.conf.SecurityConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class, SecurityConfiguration.class, HibernateTestConfiguration.class})
@WebAppConfiguration
public class SecurityTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String X_AUTH_USERNAME = "X-Auth-Username";
    private static final String X_AUTH_PASSWORD = "X-Auth-Password";
    private static final String X_AUTH_TOKEN = "X-Auth-Token";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testUrlAccessibleByEveryone() throws Exception {
        mvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testUserWithValidCredentials() throws Exception {
        mvc.perform(
                post("/authenticate")
                        .header(X_AUTH_USERNAME, "me")
                        .header(X_AUTH_PASSWORD, "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUserWithInvalidCredentials() throws Exception {
        mvc.perform(
                post("/authenticate")
                        .header(X_AUTH_USERNAME, "user1")
                        .header(X_AUTH_PASSWORD, "pass1"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void testUserWithValidToken() throws Exception {
        String responseContent = mvc.perform(
                post("/authenticate")
                        .header(X_AUTH_USERNAME, "me")
                        .header(X_AUTH_PASSWORD, "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseContent);
        String token = (String) jsonObject.get("token");

        mvc.perform(
                get("/user/1").header(X_AUTH_TOKEN, token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
