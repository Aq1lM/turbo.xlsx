package group.aist;

import group.aist.business.impl.CarServiceImpl;
import group.aist.logger.Logger;
import group.aist.logger.impl.LoggerImpl;

import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) {
        try {
            CarServiceImpl carService = new CarServiceImpl();
            carService.addCarDetailFromSite();

            Logger logger = LoggerImpl.getInstance();
            logger.logToExcel(carService.getAccessRepository());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
