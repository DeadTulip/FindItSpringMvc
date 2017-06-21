package p.hh.smvc.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import p.hh.smvc.domain.DiskLocation;
import p.hh.smvc.domain.Location;
import p.hh.smvc.domain.PhysicalLocation;

import java.util.List;

import static org.junit.Assert.*;

public class LocationDaoTest extends AbstractDaoTest {

    @Autowired
    LocationDao locationDao;

    @Test
    public void findTest() {
        assertEquals(locationDao.findAll().size(), 0);

        PhysicalLocation physicalLocation = new PhysicalLocation();
        physicalLocation.setName("tempName");
        physicalLocation.setDescription("tempDescription");
        locationDao.create(physicalLocation);
        assertEquals(locationDao.findAll().size(), 1);

        DiskLocation diskLocation = new DiskLocation();
        diskLocation.setOriginalName("tempOriginalName");
        diskLocation.setOnDiskName("tempOnDiskName");
        locationDao.create(diskLocation);
        assertEquals(locationDao.findAll().size(), 2);
    }
}
