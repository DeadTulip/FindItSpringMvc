package p.hh.smvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.hh.smvc.dao.LocationDao;
import p.hh.smvc.domain.Location;

@Transactional
@Service
public class LocationService {

    @Autowired
    private LocationDao locationDao;

    public Location findById(Long id) {
        return locationDao.findById(id);
    }
}
