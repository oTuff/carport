package dat.startcode.model.services;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.PartsListLine;
import dat.startcode.model.entities.Product;

import java.util.ArrayList;

public class Calculator {
    //what to return?? void/partslis array/int??
    // static?
    public ArrayList<PartsListLine> calcPartsList(Order order) {
        int width = order.getWidth();
        int length = order.getLength();

        ArrayList<PartsListLine> partsList = new ArrayList<>();

        //length check todo: make own method
        boolean split=false;

        if (length > 720) {
            length = length / 2;
            split=true;
        }

        int brædeLength= length;

        int quantity=2;
        //splitcheck
        if (split) {
            quantity=4;
            brædeLength=length/2;
        }

        //todo fix description.
        //sternbræder
        PartsListLine p = new PartsListLine(getProduct(1), width, 2, "understernbrædder til for & bag ende");
        partsList.add(p);
        PartsListLine p2 = new PartsListLine(getProduct(1), brædeLength, quantity, "understernbrædder til siderne");
        partsList.add(p2);
        PartsListLine p3 = new PartsListLine(getProduct(2), width, 1, "oversternbrædde til forenden");
        partsList.add(p3);
        PartsListLine p4 = new PartsListLine(getProduct(2), brædeLength, quantity, "oversternbrædde til siderne");
        partsList.add(p4);

        //spærtræ
        int spærQuantity = (int) Math.ceil(length / 52);
        PartsListLine p5 = new PartsListLine(getProduct(3), length, quantity, "Remme i sider, sadles ned i stolper");
        partsList.add(p5);

        PartsListLine p6 = new PartsListLine(getProduct(3), width, spærQuantity, "Remme i sider, sadles ned i stolper");
        partsList.add(p6);

        //stolpe
        int stolpeQuantity = (int) Math.ceil(length / 300) * 2;
        PartsListLine p7 = new PartsListLine(getProduct(4), width, stolpeQuantity, "Stolper nedgraves 90 cm. i jord");
        partsList.add(p7);

        //vandbræt
        PartsListLine p8 = new PartsListLine(getProduct(5), brædeLength, quantity, "vandbrædt på stern i sider");
        partsList.add(p8);
        PartsListLine p9 = new PartsListLine(getProduct(5), width, 1, "vandbrædt på stern i forende");
        partsList.add(p9);

        //tagplader
        int tagQuant = (int) Math.ceil(width / 100);

        PartsListLine p10 = new PartsListLine(getProduct(6), 600, tagQuant, "tagplader monteres på spær");
        partsList.add(p10);
        if (length > 600 && length <= 960) {
            PartsListLine p11 = new PartsListLine(getProduct(6), 360, tagQuant, "tagplader monteres på spær");
            partsList.add(p11);
            tagQuant = tagQuant * 2;
        } else if (length > 960) {
            PartsListLine p12 = new PartsListLine(getProduct(6), 600, tagQuant, "tagplader monteres på spær");
            partsList.add(p12);
            tagQuant = tagQuant * 2;
        }

        //skruer mv.
        PartsListLine p13 = new PartsListLine(getProduct(7), 0, (int) Math.ceil(tagQuant / 4), "Skruer til tagplader");
        partsList.add(p13);
        PartsListLine p14 = new PartsListLine(getProduct(8), 0, 2, "Til vindkryds på spær");
        partsList.add(p14);
        PartsListLine p15 = new PartsListLine(getProduct(9), 0, spærQuantity, "Til montering af spær på rem");
        partsList.add(p15);
        PartsListLine p16 = new PartsListLine(getProduct(10), 0, spærQuantity, "Til montering af spær på rem");
        partsList.add(p16);
        PartsListLine p17 = new PartsListLine(getProduct(11), 0, 1, "Til montering af stern&vandbrædt");
        partsList.add(p17);
        PartsListLine p18 = new PartsListLine(getProduct(12), 0, (int) Math.ceil(spærQuantity / 5), "Til montering af universalbeslag + hulbånd");
        partsList.add(p18);
        PartsListLine p19 = new PartsListLine(getProduct(13), 0, (int) Math.ceil(stolpeQuantity * 3), "Til montering af rem på stolper");
        partsList.add(p19);
        PartsListLine p20 = new PartsListLine(getProduct(14), 0, (int) Math.ceil(stolpeQuantity * 2), "Til montering af rem på stolper");
        partsList.add(p20);
        PartsListLine p21 = new PartsListLine(getProduct(15), 0, 2, "Til montering af yderste beklæding");
        partsList.add(p21);
        PartsListLine p22 = new PartsListLine(getProduct(16), 0, 2, "Til montering af inderste beklædning");
        partsList.add(p22);

        order.setPartsListLines(partsList);
        return partsList;
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