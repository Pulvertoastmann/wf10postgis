/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.demo.services;

import de.demo.entities.Location;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Toaster
 */
@Stateless
public class LocationService {

    @PersistenceContext
    EntityManager em;

    public List<Location> allLocations() {
        List<Location> resultList = em.createNamedQuery("de.demo.location.findall", Location.class).getResultList();
        return resultList;
    }

    public void persistLocation(Location location) {
        em.persist(location);
    }

}
