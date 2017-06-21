package p.hh.smvc.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import p.hh.smvc.domain.DiskLocation;

import static org.junit.Assert.*;



public class DiskLocationDaoTest extends AbstractDaoTest {

    @Autowired
    DiskLocationDao diskLocationDao;

    @Test
    public void findOneTest() {
        DiskLocation diskLocation = new DiskLocation();
        diskLocation.setOriginalName("tempOriginalName");
        diskLocation.setOnDiskName("tempOnDiskName");

        diskLocationDao.create(diskLocation);

        assertNotNull(diskLocation.getId());

        DiskLocation foundDiskLocation = diskLocationDao.findById(diskLocation.getId());

        assertEquals(foundDiskLocation.getOnDiskName(), diskLocation.getOnDiskName());
        assertEquals(foundDiskLocation.getOriginalName(), diskLocation.getOriginalName());
    }
}
