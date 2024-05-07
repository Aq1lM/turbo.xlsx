package group.aist.logger.impl;

import group.aist.entities.Car;
import group.aist.entities.Model;
import group.aist.inMemory.CarRepository;
import group.aist.logger.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

/**
 * Service class containing business logic for logging
 *
 * @author AqilM
 */

public class LoggerImpl implements Logger {
    private static LoggerImpl instance;
    private final String filePath = "cars.xlsx";
    private final File file = new File(filePath);

    private LoggerImpl() {
    }

    /**
     * write data from CarRepository to Excel file
     *
     * @param carRepository
     */

    @Override
    public void logToExcel(CarRepository carRepository) {

        try (FileOutputStream fos = new FileOutputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet sheet = workbook.createSheet("CarSheet");

            Car car;
            int rowCount = 0;

            Iterator<Map.Entry<String, Car>> it = carRepository.carMap.entrySet().iterator();
            while (it.hasNext()) {
                XSSFRow row = sheet.createRow(rowCount++);

                int cellCount = 0;
                car = it.next().getValue();

                Object[] header = getHeader();
                Object[] rowDatas = getCarInfos(car);

                if (rowCount == 1) {
                    for (Object o : header) {
                        XSSFCell cell = row.createCell(cellCount++);
                        if (o instanceof String)
                            cell.setCellValue(String.valueOf(o).substring(3));
                    }
                } else {
                    for (Object o : rowDatas) {
                        XSSFCell cell = row.createCell(cellCount++);
                        if (o instanceof String)
                            cell.setCellValue((String) o);
                        if (o instanceof Integer)
                            cell.setCellValue((Integer) o);
                        if (o instanceof Boolean)
                            cell.setCellValue((Boolean) o);
                        if (o instanceof BigDecimal)
                            cell.setCellValue(((BigDecimal) o).doubleValue());
                    }
                }
            }

            workbook.write(fos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * create an Object array that contains properties of Car obj
     *
     * @param car - Car obj
     * @return Object array
     */

    private Object[] getCarInfos(Car car) {
        return new Object[]{
                car.getCity(),
                car.getModel().getBrand().getBrand(),
                car.getModel().getModel(),
                car.getProductionYear(),
                car.getBody(),
                car.getColor(),
                car.getEngine(),
                car.getMarch(),
                car.getTransmission(),
                car.getDriveType(),
                car.getNew(),
                car.getSeatCount(),
                car.getOwners(),
                car.getSituation(),
                car.getTradeRegion(),
                car.getFullPrice()
        };
    }

    /**
     * create an Object array that contains DeclaredMethodNames of Car class for header
     *
     * @return Object array
     * @throws NoSuchMethodException
     */

    private Object[] getHeader() throws NoSuchMethodException {

        return new Object[]{
                Car.class.getDeclaredMethod("getCity").getName().toUpperCase(),
                Model.class.getDeclaredMethod("getBrand").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getModel").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getProductionYear").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getBody").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getColor").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getEngine").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getMarch").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getTransmission").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getDriveType").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getNew").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getSeatCount").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getOwners").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getSituation").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getTradeRegion").getName().toUpperCase(),
                Car.class.getDeclaredMethod("getFullPrice").getName().toUpperCase(),
        };
    }

    /**
     * create a single instance of LoggerImpl class
     *
     * @return instance of LoggerImpl
     * @throws FileNotFoundException
     */

    public static LoggerImpl getInstance() throws FileNotFoundException {
        if (instance == null)
            synchronized (LoggerImpl.class) {
                if (instance == null)
                    instance = new LoggerImpl();
            }
        return instance;
    }
}
