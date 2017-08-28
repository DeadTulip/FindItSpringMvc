package p.hh.smvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import net.minidev.json.JSONObject;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Test;
import org.springframework.http.MediaType;
import p.hh.smvc.domain.User;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PostRequestControllerTest extends AbstractControllerTest {

    @Test
    public void testResponseJson() throws Exception {
        JsonObject jsonObject = JsonBuilderFactory.buildObject()
                .add("username", "TestUserName")
                .add("password", "TestPassword")
                .getJson();

        this.mockMvc.perform(post("/user/create").contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
