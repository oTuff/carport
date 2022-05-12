package dat.startcode.model.entities;

import java.util.ArrayList;

public class Order {
    private User user;
    private int partslistOrderId;
    String email;
    private int width;
    private int length;
    private int orderPrice;
    private int shedId;
    private boolean accepted;
    private ArrayList<PartsListLine> partsListLines;

    public Order(User user, int width, int length) {
        this.user = user;
        this.width = width;
        this.length = length;
        this.partsListLines = new ArrayList<>();
    }

    public Order(int partslistOrderId, String email, int width, int length, int orderPrice, int shedId, boolean accepted) {
        this.partslistOrderId = partslistOrderId;
        this.email = email;
        this.width = width;
        this.length = length;
        this.orderPrice = orderPrice;
        this.shedId = shedId;
        this.accepted = accepted;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPartslistOrderId() {
        return partslistOrderId;
    }

    public void setPartslistOrderId(int partslistOrderId) {
        this.partslistOrderId = partslistOrderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getShedId() {
        return shedId;
    }

    public void setShedId(int shedId) {
        this.shedId = shedId;
    }

    public ArrayList<PartsListLine> getPartsListLines() {
        return partsListLines;
    }

    public void setPartsListLines(ArrayList<PartsListLine> partsListLines) {
        this.partsListLines = partsListLines;
    }
}
