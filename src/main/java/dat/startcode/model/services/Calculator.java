package dat.startcode.model.services;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.PartsListLine;
import dat.startcode.model.entities.Product;

import java.util.ArrayList;

public class Calculator {
    private Order order;
    private int width;
    private int length;
    private ArrayList<PartsListLine> partsList;
    private int postQuantity;
    private int rafterQuantity;
    private int boardQuantity = 2;
    private int boardLength;
    private ArrayList<Product> products;

    public Calculator(Order order, ArrayList<Product> products) {
        this.order = order;
        this.width = order.getWidth();
        this.length = order.getLength();
        this.partsList = new ArrayList<>();
        this.boardLength = length;
        this.products = products;
    }

    public void calcPartsList() {
        splitCheck();

        //todo fix description. get from description arraylist from db.
        //sternbræder
        partsList.add(new PartsListLine(products.get(0), width, 2, "understernbrædder til for & bag ende"));
        partsList.add(new PartsListLine(products.get(0), boardLength, boardQuantity, "understernbrædder til siderne"));
        partsList.add(new PartsListLine(products.get(1), width, 1, "oversternbrædde til forenden"));
        partsList.add(new PartsListLine(products.get(1), boardLength, boardQuantity, "oversternbrædde til siderne"));

        //spærtræ
        calcRafterQuantity();
        partsList.add(new PartsListLine(products.get(2), boardLength, boardQuantity, "Remme i sider, sadles ned i stolper"));
        partsList.add(new PartsListLine(products.get(2), width, rafterQuantity, "Remme i sider, sadles ned i stolper"));

        //stolper
        calcPostsQuantity();
        partsList.add(new PartsListLine(products.get(3), 300, postQuantity, "Stolper nedgraves 90 cm. i jord"));

        //vandbræt
        partsList.add(new PartsListLine(products.get(4), boardLength, boardQuantity, "vandbrædt på stern i sider"));
        partsList.add(new PartsListLine(products.get(4), width, 1, "vandbrædt på stern i forende"));

        //tagplader
        calcRoofing();

        //skruer mv.
        partsList.add(new PartsListLine(products.get(7), 0, 2, "Til vindkryds på spær"));
        partsList.add(new PartsListLine(products.get(8), 0, rafterQuantity, "Til montering af spær på rem"));
        partsList.add(new PartsListLine(products.get(9), 0, rafterQuantity, "Til montering af spær på rem"));
        partsList.add(new PartsListLine(products.get(10), 0, 1, "Til montering af stern&vandbrædt"));
        partsList.add(new PartsListLine(products.get(11), 0, (int) Math.ceil(rafterQuantity / 5.0), "Til montering af universalbeslag + hulbånd"));
        partsList.add(new PartsListLine(products.get(12), 0, (int) Math.ceil(postQuantity * 3.0), "Til montering af rem på stolper"));
        partsList.add(new PartsListLine(products.get(13), 0, (int) Math.ceil(postQuantity * 2.0), "Til montering af rem på stolper"));
        partsList.add(new PartsListLine(products.get(14), 0, 2, "Til montering af yderste beklæding"));
        partsList.add(new PartsListLine(products.get(15), 0, 2, "Til montering af inderste beklædning"));

        calcPrice();

        order.setPartsListLines(partsList);
    }

    private void splitCheck() {//if length is longer than 720 which is the longest board you need two boards.
        if (length > 720) {
            boardQuantity = 4;//doubles the quantity
            boardLength = length / 2;//split the board length in half(a post should be in the middle)
        }
    }

    private void calcRafterQuantity() {
        rafterQuantity = (int) Math.ceil(length / 52.0);
    }

    private void calcPostsQuantity() {
        postQuantity = (int) Math.ceil(length / 300.0) * 2;
        if (postQuantity < 4) {
            postQuantity = 4;
        }
    }

    private void calcRoofing() {
        int roofQuantity = (int) Math.ceil(width / 100.0);// roof is 120 wide and need to overlap with 20.

        //you will always get 600cm roof. even if you only need 350.
        partsList.add(new PartsListLine(products.get(5), 600, roofQuantity, "tagplader monteres på spær"));
        if (length > 600) {
            partsList.add(new PartsListLine(products.get(5), 360, roofQuantity, "tagplader monteres på spær"));
            roofQuantity = roofQuantity * 2;
        }
        partsList.add(new PartsListLine(products.get(6), 0, (int) Math.ceil(roofQuantity / 4.0), "Skruer til tagplader"));
    }

    private void calcLinePrice(PartsListLine l) {//calculates the price by multiplying the product price with quantity and the length(partsListLine has a length)
        if (l.getLength() == 0) {
            l.setTotalPrice(l.getProduct().getPrice() * l.getQuantity());
        } else
            l.setTotalPrice(l.getProduct().getPrice() * l.getLength()/100 * l.getQuantity());
    }

    private void calcPrice() {
        int price = 0;
        for (PartsListLine l : partsList) {
            calcLinePrice(l);
            price = price + l.getTotalPrice();
        }
        order.setOrderPrice(price);
    }

    public int getPostQuantity() {
        return postQuantity;
    }

    public int getRafterQuantity() {
        return rafterQuantity;
    }
}