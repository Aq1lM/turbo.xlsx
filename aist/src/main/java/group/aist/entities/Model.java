package group.aist.entities;

/**
 * Model class
 *
 * @author AqilM
 */

public class Model {
    private Brand brand;

    private String model;

    public Model() {
    }

    public Model(Brand brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Model{" +
                "brand=" + brand.getBrand() +
                ", model='" + model + '\'' +
                '}';
    }
}
