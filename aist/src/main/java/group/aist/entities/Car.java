package group.aist.entities;

import java.math.BigDecimal;

/**
 * Car class
 *
 * @author AqilM
 */

public class Car {
    private Model model;

    private String city;
    private String color;
    private String body;
    private String engine;
    private String transmission;
    private String driveType;
    private String situation;
    private String tradeRegion;
    private String currency;
    private String seatCount;
    private String owners;

    private Integer march;
    private Integer productionYear;

    private Boolean isNew;

    private BigDecimal price;

    public Car() {
    }

    public Car(String city, String color, String body, String engine, String transmission,
               String driveType, String situation, String tradeRegion, String currency, String seatCount, String owners,
               Integer march, Integer productionYear,
               Boolean isNew,
               BigDecimal price,
               Model model) {

        this.city = city;
        this.color = color;
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
        this.driveType = driveType;
        this.situation = situation;
        this.tradeRegion = tradeRegion;
        this.currency = currency;
        this.seatCount = seatCount;
        this.march = march;
        this.productionYear = productionYear;
        this.isNew = isNew;
        this.price = price;
        this.model = model;
        this.owners = owners;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getTradeRegion() {
        return tradeRegion;
    }

    public void setTradeRegion(String tradeRegion) {
        this.tradeRegion = tradeRegion;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getMarch() {
        return march;
    }

    public void setMarch(Integer march) {
        this.march = march;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getNew() {
        return isNew ? "Bəli" : "Xeyr";
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    /**
     * get fullPrice that contains price and currency
     *
     * @return fullPrice
     */

    public String getFullPrice() {
        return price.toString() + " " + currency;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand=" + model.getBrand().getBrand() +
                ", model=" + model.getModel() +
                ", city='" + city + '\'' +
                ", color='" + color + '\'' +
                ", body='" + body + '\'' +
                ", engine='" + engine + '\'' +
                ", transmission='" + transmission + '\'' +
                ", driveType='" + driveType + '\'' +
                ", situation='" + situation + '\'' +
                ", tradeRegion='" + tradeRegion + '\'' +
                ", seatCount=" + seatCount +
                ", march=" + march +
                ", productionYear=" + productionYear +
                ", isNew=" + getNew() +
                ", price=" + getFullPrice() +
                '}';
    }
}
