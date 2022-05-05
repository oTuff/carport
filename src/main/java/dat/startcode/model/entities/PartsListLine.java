package dat.startcode.model.entities;

public class PartsListLine {
    private Product product;
    private String Length;
    private int quantity;
    private String unit;
    private String Description;
    private String totalPrice;

    public PartsListLine(Product product, int quantity, String unit, String description, String totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
        Description = description;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
