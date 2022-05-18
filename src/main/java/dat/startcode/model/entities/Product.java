package dat.startcode.model.entities;

public class Product {
    private int productId;
    private String name;
    private int price;
    private String unit;

    public Product(String name, int price, String unit) {
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }
}
