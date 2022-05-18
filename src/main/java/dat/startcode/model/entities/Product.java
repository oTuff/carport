package dat.startcode.model.entities;

public class Product {
    private int productId;
    private String name;
    private int price;
    private String unit;
    private int unitId;

    public Product(String name, int price, String unit) {
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    public Product(int productId, String name, int price, int unitId) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.unitId = unitId;
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

<<<<<<< HEAD
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
=======
    public int getProductId() {
        return productId;
    }
>>>>>>> 451af65 (added product_id and partslistlinemapper function to write to db)
}
