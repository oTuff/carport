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
    private int postQuantity = 2;
    private int rafterQuantity;
    private int boardQuantity;
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

    //what to return?? void/partslis array/int??
    // static?
    public ArrayList<PartsListLine> calcPartsList() {

        splitCheck();

        //todo fix description. get from description arraylist from db.
        //sternbræder
        PartsListLine p = new PartsListLine(products.get(0), width, 2, "understernbrædder til for & bag ende");
        partsList.add(p);
        PartsListLine p2 = new PartsListLine(products.get(0), boardLength, boardQuantity, "understernbrædder til siderne");
        partsList.add(p2);
        PartsListLine p3 = new PartsListLine(products.get(1), width, 1, "oversternbrædde til forenden");
        partsList.add(p3);
        PartsListLine p4 = new PartsListLine(products.get(1), boardLength, boardQuantity, "oversternbrædde til siderne");
        partsList.add(p4);

        //spærtræ
        calcRafterQuantity();

        PartsListLine p5 = new PartsListLine(products.get(3), boardLength, boardQuantity, "Remme i sider, sadles ned i stolper");
        partsList.add(p5);
        PartsListLine p6 = new PartsListLine(products.get(3), width, rafterQuantity, "Remme i sider, sadles ned i stolper");
        partsList.add(p6);

        //stolper
        calcPosts();

        //vandbræt
        PartsListLine p8 = new PartsListLine(products.get(5), boardLength, boardQuantity, "vandbrædt på stern i sider");
        partsList.add(p8);
        PartsListLine p9 = new PartsListLine(products.get(5), width, 1, "vandbrædt på stern i forende");
        partsList.add(p9);

        //tagplader
        calcRoofing();

        //skruer mv.
        PartsListLine p14 = new PartsListLine(products.get(8), 0, 2, "Til vindkryds på spær");
        partsList.add(p14);
        PartsListLine p15 = new PartsListLine(products.get(9), 0, rafterQuantity, "Til montering af spær på rem");
        partsList.add(p15);
        PartsListLine p16 = new PartsListLine(products.get(10), 0, rafterQuantity, "Til montering af spær på rem");
        partsList.add(p16);
        PartsListLine p17 = new PartsListLine(products.get(11), 0, 1, "Til montering af stern&vandbrædt");
        partsList.add(p17);
        PartsListLine p18 = new PartsListLine(products.get(12), 0, (int) Math.ceil(rafterQuantity / 5.0), "Til montering af universalbeslag + hulbånd");
        partsList.add(p18);
        PartsListLine p19 = new PartsListLine(products.get(13), 0, (int) Math.ceil(postQuantity * 3.0), "Til montering af rem på stolper");
        partsList.add(p19);
        PartsListLine p20 = new PartsListLine(products.get(14), 0, (int) Math.ceil(postQuantity * 2.0), "Til montering af rem på stolper");
        partsList.add(p20);
        PartsListLine p21 = new PartsListLine(products.get(15), 0, 2, "Til montering af yderste beklæding");
        partsList.add(p21);
        PartsListLine p22 = new PartsListLine(products.get(16), 0, 2, "Til montering af inderste beklædning");
        partsList.add(p22);

        calcPrice();

        order.setPartsListLines(partsList);
        return partsList;
    }

    private boolean splitCheck() {
        boolean isSplit = false;
        if (length > 720) {
            isSplit = true;
            boardQuantity = 4;
            boardLength = length / 2;
        }
        return isSplit;
    }

    private void calcRafterQuantity() {
        rafterQuantity = (int) Math.ceil(length / 52.0);
    }

    private void calcPosts() {
        postQuantity = (int) Math.ceil(length / 300.0) * 2;
        if (postQuantity < 4) {
            postQuantity = 4;
        }

        partsList.add(new PartsListLine(products.get(4), width, postQuantity, "Stolper nedgraves 90 cm. i jord"));
    }

    private void calcRoofing() {
        int roofQuantity = (int) Math.ceil(width / 100.0);

        partsList.add(new PartsListLine(products.get(6), 600, roofQuantity, "tagplader monteres på spær"));
        if (length > 600 && length <= 960) {
            partsList.add(new PartsListLine(products.get(6), 360, roofQuantity, "tagplader monteres på spær"));
            roofQuantity = roofQuantity * 2;
        } else if (length > 960) {
            partsList.add(new PartsListLine(products.get(6), 600, roofQuantity, "tagplader monteres på spær"));
            roofQuantity = roofQuantity * 2;
        }
        partsList.add(new PartsListLine(products.get(7), 0, (int) Math.ceil(roofQuantity / 4.0), "Skruer til tagplader"));
    }

    public void calcLinePrice(PartsListLine l) {
        if (l.getLength() == 0) {
            l.setTotalPrice(l.getProduct().getPrice() * l.getQuantity());
        } else
            l.setTotalPrice(l.getProduct().getPrice() * l.getLength() * l.getQuantity());
    }

    public void calcPrice() {
        int price = 0;
        for (PartsListLine l : partsList) {
            calcLinePrice(l);
            price = price + l.getTotalPrice();
        }
        order.setOrderPrice(price);
    }
}