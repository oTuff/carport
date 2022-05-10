package dat.startcode.model.entities;

public class PartsListLine {
    private Product product;
    private int Length;
    private int quantity;
    private String unit;
    private String Description;
    private int totalPrice;

    public PartsListLine(Product product, int quantity, String unit, String description) {
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
        this.Description = description;
        this.totalPrice = calcPrice();
    }

    public PartsListLine(Product product, int length, int quantity, String unit, String description,) {
        this.product = product;
        Length = length;
        this.quantity = quantity;
        this.unit = unit;
        Description = description;
        this.totalPrice = calcPrice();
    }

    public int calcPrice() { // move to calculator!
        int price = totalPrice;
        if (getLength() == 0) {
            price = product.getPrice() * getQuantity();
        } else
            price = product.getPrice() * getLength() * getQuantity();
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
