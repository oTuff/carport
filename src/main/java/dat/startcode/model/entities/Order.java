package dat.startcode.model.entities;

import java.util.ArrayList;
import java.util.Objects;

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

    public Order(int partslistOrderId, User user, int width, int length, int orderPrice, int shedId, boolean accepted) {
        this.partslistOrderId = partslistOrderId;
        this.user = user;
        this.width = width;
        this.length = length;
        this.orderPrice = orderPrice;
        this.shedId = shedId;
        this.accepted = accepted;
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getPartslistOrderId() == order.getPartslistOrderId() && getWidth() == order.getWidth() && getLength() == order.getLength() && getOrderPrice() == order.getOrderPrice() && getShedId() == order.getShedId() && isAccepted() == order.isAccepted() && Objects.equals(getUser(), order.getUser()) && Objects.equals(getEmail(), order.getEmail()) && Objects.equals(getPartsListLines(), order.getPartsListLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getPartslistOrderId(), getEmail(), getWidth(), getLength(), getOrderPrice(), getShedId(), isAccepted(), getPartsListLines());
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", partslistOrderId=" + partslistOrderId +
                ", email='" + email + '\'' +
                ", width=" + width +
                ", length=" + length +
                ", orderPrice=" + orderPrice +
                ", shedId=" + shedId +
                ", accepted=" + accepted +
                ", partsListLines=" + partsListLines +
                '}';
    }
}
