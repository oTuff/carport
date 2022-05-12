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
    private int boardQuantity;
    private int boardLength;

    public Calculator(Order order) {
        this.order = order;
        this.width = order.getWidth();
        this.length = order.getLength();
        this.partsList = new ArrayList<>();
    }

    //what to return?? void/partslis array/int??
    // static?
    public ArrayList<PartsListLine> calcPartsList() {

        splitCheck();

        //todo fix description.
        //sternbræder
        PartsListLine p = new PartsListLine(getProduct(1), width, 2, "understernbrædder til for & bag ende");
        partsList.add(p);
        PartsListLine p2 = new PartsListLine(getProduct(1), boardLength, boardQuantity, "understernbrædder til siderne");
        partsList.add(p2);
        PartsListLine p3 = new PartsListLine(getProduct(2), width, 1, "oversternbrædde til forenden");
        partsList.add(p3);
        PartsListLine p4 = new PartsListLine(getProduct(2), boardLength, boardQuantity, "oversternbrædde til siderne");
        partsList.add(p4);

        calcRafterQuantity();

        //spærtræ
        PartsListLine p5 = new PartsListLine(getProduct(3), boardLength, boardQuantity, "Remme i sider, sadles ned i stolper");
        partsList.add(p5);
        PartsListLine p6 = new PartsListLine(getProduct(3), width, rafterQuantity, "Remme i sider, sadles ned i stolper");
        partsList.add(p6);

        calcPosts();

        //vandbræt
        PartsListLine p8 = new PartsListLine(getProduct(5), boardLength, boardQuantity, "vandbrædt på stern i sider");
        partsList.add(p8);
        PartsListLine p9 = new PartsListLine(getProduct(5), width, 1, "vandbrædt på stern i forende");
        partsList.add(p9);

        calcRoofing();

        //skruer mv.
        PartsListLine p14 = new PartsListLine(getProduct(8), 0, 2, "Til vindkryds på spær");
        partsList.add(p14);
        PartsListLine p15 = new PartsListLine(getProduct(9), 0, rafterQuantity, "Til montering af spær på rem");
        partsList.add(p15);
        PartsListLine p16 = new PartsListLine(getProduct(10), 0, rafterQuantity, "Til montering af spær på rem");
        partsList.add(p16);
        PartsListLine p17 = new PartsListLine(getProduct(11), 0, 1, "Til montering af stern&vandbrædt");
        partsList.add(p17);
        PartsListLine p18 = new PartsListLine(getProduct(12), 0, (int) Math.ceil(rafterQuantity / 5), "Til montering af universalbeslag + hulbånd");
        partsList.add(p18);
        PartsListLine p19 = new PartsListLine(getProduct(13), 0, (int) Math.ceil(postQuantity * 3), "Til montering af rem på stolper");
        partsList.add(p19);
        PartsListLine p20 = new PartsListLine(getProduct(14), 0, (int) Math.ceil(postQuantity * 2), "Til montering af rem på stolper");
        partsList.add(p20);
        PartsListLine p21 = new PartsListLine(getProduct(15), 0, 2, "Til montering af yderste beklæding");
        partsList.add(p21);
        PartsListLine p22 = new PartsListLine(getProduct(16), 0, 2, "Til montering af inderste beklædning");
        partsList.add(p22);

        order.setPartsListLines(partsList);
        return partsList;
    }

    private void splitCheck(){
        if (length > 720) {
            boardQuantity = 4;
            boardLength = length / 2;
        }
    }

    private void calcRafterQuantity() {
        rafterQuantity = (int) Math.ceil(length / 52);
    }

    private void calcPosts() {
        postQuantity = (int) Math.ceil(length / 300) * 2;

        partsList.add(new PartsListLine(getProduct(4), width, postQuantity, "Stolper nedgraves 90 cm. i jord"));
    }

    private void calcRoofing() {
        int roofQuantity = (int) Math.ceil(width / 100);

        partsList.add(new PartsListLine(getProduct(6), 600, roofQuantity, "tagplader monteres på spær"));
        if (length > 600 && length <= 960) {
            partsList.add(new PartsListLine(getProduct(6), 360, roofQuantity, "tagplader monteres på spær"));
            roofQuantity = roofQuantity * 2;
        } else if (length > 960) {
            partsList.add(new PartsListLine(getProduct(6), 600, roofQuantity, "tagplader monteres på spær"));
            roofQuantity = roofQuantity * 2;
        }
        partsList.add(new PartsListLine(getProduct(7), 0, (int) Math.ceil(roofQuantity / 4), "Skruer til tagplader"));
    }


    private Product getProduct(int id) {//from db
        String productName = "";//get product name fra db
        int productPrice = 0;// get product price from db
        String productUnit = "";//get product unit fra db
        Product product = new Product(productName, productPrice, productUnit);

        return product;
    }

    private String getDescription(int id) {//from db
        String description = "";// get from db.

        return description;
    }

}