package group.aist.logger;

import group.aist.inMemory.CarRepository;

/**
 * Service interface containing functions for logging
 *
 * @author AqilM
 */

public interface Logger {
    void logToExcel(CarRepository carRepository);
}
