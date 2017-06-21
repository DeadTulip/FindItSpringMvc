package p.hh.smvc.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import p.hh.smvc.domain.DiskLocation;

import static org.junit.Assert.*;

public class DiskLocationDaoTest extends AbstractDaoTest {

    @Autowired
    DiskLocationDao diskLocationDao;

    @Test
    public void findTest() {
        DiskLocation diskLocation = new DiskLocation();
        diskLocation.setOriginalName("tempOriginalName");
        diskLocation.setOnDiskName("tempOnDiskName");
        assertNull(diskLocation.getId());

        diskLocationDao.create(diskLocation);
        assertNotNull(diskLocation.getId());

        DiskLocation foundDiskLocation = diskLocationDao.findById(diskLocation.getId());
        assertEquals(foundDiskLocation.getOnDiskName(), diskLocation.getOnDiskName());
        assertEquals(foundDiskLocation.getOriginalName(), diskLocation.getOriginalName());
    }

    @Test
    public void updateTest() {
        DiskLocation diskLocation = new DiskLocation();
        diskLocation.setOriginalName("tempOriginalName");
        diskLocation.setOnDiskName("tempOnDiskName");
        assertNull(diskLocation.getId());

        diskLocationDao.create(diskLocation);
        assertNotNull(diskLocation.getId());

        diskLocation.setOriginalName("newOriginalName");
        diskLocationDao.update(diskLocation);
        DiskLocation foundDiskLocation = diskLocationDao.findById(diskLocation.getId());
        assertEquals(foundDiskLocation.getOriginalName(), "newOriginalName");
        assertEquals(foundDiskLocation.getOnDiskName(), "tempOnDiskName");
    }

    @Test
    public void deleteTest() {
        DiskLocation diskLocation = new DiskLocation();
        diskLocation.setOriginalName("tempOriginalName");
        diskLocation.setOnDiskName("tempOnDiskName");
        assertNull(diskLocation.getId());

        diskLocationDao.create(diskLocation);
        assertNotNull(diskLocation.getId());

        DiskLocation foundDiskLocation = diskLocationDao.findById(diskLocation.getId());
        assertNotNull(foundDiskLocation);

        diskLocationDao.deleteById(diskLocation.getId());
        DiskLocation foundAgainDiskLocation = diskLocationDao.findById(diskLocation.getId());
        assertNull(foundAgainDiskLocation);
    }
}
