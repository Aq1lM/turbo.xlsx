package group.aist.entities;

/**
 * Brand class
 *
 * @author AqilM
 */

public class Brand {
    private String brand;

    public Brand() {
    }

    public Brand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
