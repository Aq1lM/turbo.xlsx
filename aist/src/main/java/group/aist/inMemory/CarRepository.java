package group.aist.inMemory;

import group.aist.entities.Car;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Repository class containing InMemoryRepository - LinkedHashMap
 *
 * @author AqilM
 */

public class CarRepository {
    public Map<String, Car> carMap = new LinkedHashMap<>();
}
