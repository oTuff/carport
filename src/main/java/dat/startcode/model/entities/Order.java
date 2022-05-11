package dat.startcode.model.entities;

import java.util.ArrayList;

public class Order {// refactor to be able to hold shed - make shed object??!
    private User user;
    private int partsListOrderId;
    String email;
    private int width;
    private int length;
    private int orderPrice;
    int shedId;
    private ArrayList<PartsListLine> partsListLines;


    public Order(User user, int width, int length) {
        this.user = user;
        this.width = width;
        this.length = length;
        this.partsListLines = new ArrayList<>();
    }

    public Order(int partslistOrderId, String email, int width, int length, int orderPrice, int shedId) {
        this.partsListOrderId = partslistOrderId;
        this.email = email;
        this.width = width;
        this.length = length;
        this.orderPrice = orderPrice;
        this.shedId = shedId;
    }

    public int calcPrice(){// move to calculator!
        int price=this.orderPrice;
        for ( PartsListLine l: partsListLines) {
           price=price+ l.getTotalPrice();
        }
        this.orderPrice=price;
        return price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList<PartsListLine> getPartsListLines() {
        return partsListLines;
    }

    public void setPartsListLines(ArrayList<PartsListLine> partsListLines) {
        this.partsListLines = partsListLines;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
