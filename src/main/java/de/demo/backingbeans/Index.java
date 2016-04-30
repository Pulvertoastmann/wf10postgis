/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.demo.backingbeans;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import de.demo.entities.Location;
import de.demo.services.LocationService;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Toaster
 */
@ManagedBean(name = "index")
@ViewScoped
public class Index {
    
    @EJB
    LocationService ls;
    
    private List<Location> locations;
    private Double newLat;
    private Double newLon;
    private String newDescription;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Double getNewLat() {
        return newLat;
    }

    public void setNewLat(Double newLat) {
        this.newLat = newLat;
    }

    public Double getNewLon() {
        return newLon;
    }

    public void setNewLon(Double newLon) {
        this.newLon = newLon;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
    
    @PostConstruct
    public void init() {
        locations = ls.allLocations();
    }
    
    public void addLocation() {
        GeometryFactory gf = new GeometryFactory();    
        Point point = gf.createPoint(new Coordinate(newLon, newLat));
        point.setSRID(4326);
        
        Location newLocation = new Location();
        newLocation.setId(UUID.randomUUID().toString());
        newLocation.setLocation(point);
        newLocation.setDescription(newDescription);
        
        ls.persistLocation(newLocation);
        
        init();
    }
    
}
