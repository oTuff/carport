package dat.startcode.model.entities;

import java.util.Objects;

public class Product {
    private int productId;
    private String name;
    private int price;
    private String unit;
    private int unitId;

    public Product(int productId, String name, int price, String unit) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProductId() == product.getProductId() && getPrice() == product.getPrice() && getUnitId() == product.getUnitId() && Objects.equals(getName(), product.getName()) && Objects.equals(getUnit(), product.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getName(), getPrice(), getUnit(), getUnitId());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", unitId=" + unitId +
                '}';
    }
}


