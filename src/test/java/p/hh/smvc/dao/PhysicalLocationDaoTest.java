package p.hh.smvc.dao;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import p.hh.smvc.domain.PhysicalLocation;

import static org.junit.Assert.*;

public class PhysicalLocationDaoTest extends AbstractDaoTest {

    @Autowired
    PhysicalLocationDao physicalLocationDao;

    @Test
    public void findTest() {
        PhysicalLocation physicalLocation = new PhysicalLocation();
        physicalLocation.setName("tempName");
        physicalLocation.setDescription("tempDescription");
        physicalLocationDao.create(physicalLocation);

        assertNotNull(physicalLocation.getId());

        PhysicalLocation foundLocation = physicalLocationDao.findById(physicalLocation.getId());
        assertEquals(foundLocation.getName(), "tempName");
        assertEquals(foundLocation.getDescription(), "tempDescription");

    }
}
