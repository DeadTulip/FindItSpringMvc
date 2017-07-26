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
import p.hh.smvc.domain.*;
import p.hh.smvc.services.ItemService;
import p.hh.smvc.services.LocationService;
import p.hh.smvc.services.TeamService;
import p.hh.smvc.services.UserService;

import javax.servlet.ServletContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UserControllerTest extends AbstractControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    @Autowired
    ItemService itemService;

    @Autowired
    TeamService teamService;

    Map<String, Long> prepareData() {
        Map<String, Long> resultMap = new HashMap<>();

        Date today = new Date();

        User user = new User();
        user.setUsername("myName");
        user.setPassword("myPassword");
        resultMap.put("userId", userService.createUser(user));

        DiskLocation location = new DiskLocation();
        location.setOriginalName("myOriginalName");
        location.setOnDiskName("myDiskName");
        resultMap.put("locationId", locationService.createLocation(location));

        DiskItem item = new DiskItem();
        item.setOwner(user);
        item.setLocation(location);
        item.setName("myItemName");
        item.setDateCreated(today);
        item.setDateUpdated(today);
        item.setEventStartTime(today);
        item.setEventEndTime(today);
        item.setInvolvedPeople("myInvolvedPeople");
        item.setInvolvedPlaces("myInvolvedPlaces");
        item.setDescription("myItemDescription");
        resultMap.put("itemId", itemService.createItem(item));

        Team team = new Team();
        team.setTeamName("myTeamName");
        team.setCreator(user);
        resultMap.put("teamId", teamService.createTeam(team));

        return  resultMap;
    }

    @Test
    public void testResponseJson() throws Exception {
        Map<String, Long> resultMap = prepareData();

        this.mockMvc.perform(get("/user/" + resultMap.get("userId")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("myName"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andReturn();

        this.mockMvc.perform(get("/location/" + resultMap.get("locationId")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.@type").value("disk"))
                .andExpect(jsonPath("$.originalName").value("myOriginalName"))
                .andExpect(jsonPath("$.onDiskName").value("myDiskName"))
                .andReturn();

        this.mockMvc.perform(get("/item/" + resultMap.get("teamId")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.@type").value("disk"))
                .andExpect(jsonPath("$.owner.id").value(resultMap.get("userId").intValue()))
                .andExpect(jsonPath("$.owner.username").doesNotExist())
                .andExpect(jsonPath("$.location.id").value(resultMap.get("locationId").intValue()))
                .andExpect(jsonPath("$.location.originalName").value("myOriginalName"))
                .andReturn();

        this.mockMvc.perform(get("/user/" + resultMap.get("userId") + "/createdTeams"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(resultMap.get("teamId").intValue()))
                .andExpect(jsonPath("$[0].teamName").value("myTeamName"))
                .andExpect(jsonPath("$[0].creator.id").value(resultMap.get("userId").intValue()))
                .andReturn();

        this.mockMvc.perform(get("/user/" + resultMap.get("userId") + "/createdItems"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(resultMap.get("itemId").intValue()))
                .andExpect(jsonPath("$[0].@type").value("disk"))
                .andReturn();
    }

}
