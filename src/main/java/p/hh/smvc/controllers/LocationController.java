package p.hh.smvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import p.hh.smvc.domain.Location;
import p.hh.smvc.services.LocationService;

@RestController
@RequestMapping(value = "/location", method = RequestMethod.GET, produces = "application/json")
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/{locationId}")
    public Location getLocation(@PathVariable Long locationId) {
        return locationService.findById(locationId);
    }
}
