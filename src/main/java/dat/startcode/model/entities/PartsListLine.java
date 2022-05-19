package dat.startcode.model.entities;

public class PartsListLine {
    private Product product;
    private int length;
    private int quantity;
    private String unit;
    private String description;
    private int totalPrice;

    public PartsListLine(Product product, int quantity, String unit, String description) {
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
    }

    public PartsListLine(Product product, int length, int quantity, String description) {
        this.product = product;
        this.length = length;
        this.quantity = quantity;
        this.description = description;
    }

    public PartsListLine(Product product, int length, int quantity, String unit, String description) {
        this.product = product;
        this.length = length;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
    }

    public PartsListLine(Product product, int length, int quantity, String description, int totalPrice) {
        this.product = product;
        this.length = length;
        this.quantity = quantity;
        this.description = description;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return totalPrice;
    }

    @Override
    public String toString() {
        return "PartsListLine{" +
                "product=" + product.getName() +
                ", length=" + length +
                ", quantity=" + quantity +
                ", unit='" + product.getUnit() + '\'' +
                ", description='" + description + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
