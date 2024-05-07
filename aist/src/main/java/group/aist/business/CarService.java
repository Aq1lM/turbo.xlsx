package group.aist.business;

import group.aist.inMemory.CarRepository;

/**
 * Service interface containing functions for car
 *
 * @author AqilM
 */

public interface CarService {
    void addCarDetailFromSite();

    CarRepository getAccessRepository();
}
