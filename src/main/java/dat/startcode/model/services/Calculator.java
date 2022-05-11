package dat.startcode.model.services;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.PartsListLine;
import dat.startcode.model.entities.Product;

import java.util.ArrayList;

public class Calculator {
    //what to return?? void/partslis array/int??
    public static ArrayList<PartsListLine> calcPartsList(Order order) {
        int width = order.getWidth();
        int width2 = 0; // 2nd width is used when the total width is longer than 720, which is the longest board
        int length = order.getLength();
        int length2 = 0; //same as width2.
        ArrayList<PartsListLine> partsList = new ArrayList<>();

        //width & length check todo: make own method

        if(length>720){

        }
        if(width>720){
            width2=width/2;
            width=width2;
            //add extra spærrem og stolper i midten
        }

        //splitcheck
//        if(!(width2 ==0))
        int quantity = 2;

        //sternbræder
            //fix unit.
            // fix description.
        PartsListLine p = new PartsListLine(getProduct(1),width,2, "understernbrædder til for & bag ende");
        PartsListLine p2 = new PartsListLine(getProduct(1),length,2, "understernbrædder til siderne");

        PartsListLine p3 = new PartsListLine(getProduct(2),width,1, "oversternbrædde til forenden");
        PartsListLine p4 = new PartsListLine(getProduct(2),length,2, "oversternbrædde til siderne");

        //spærtræ
        PartsListLine p5 = new PartsListLine(getProduct(3),length,2, "Remme i sider, sadles ned i stolper");

        int spærQuantity = (int) Math.ceil(length/52);
        PartsListLine p6 = new PartsListLine(getProduct(3),width,spærQuantity, "Remme i sider, sadles ned i stolper");
        //stolpe

        int stolpeQuantity = (int) Math.ceil(length/300)*2;
        PartsListLine p7 = new PartsListLine(getProduct(4),width,stolpeQuantity, "Stolper nedgraves 90 cm. i jord");

        //vandbræt
        PartsListLine p8 = new PartsListLine(getProduct(5), length, 2,"vandbrædt på stern i sider");
        PartsListLine p9 = new PartsListLine(getProduct(5),width,1, "vandbrædt på stern i forende");

        //tagplader
        int tagQuant = (int) Math.ceil(width/100);

        PartsListLine p10 = new PartsListLine(getProduct(6),600,tagQuant, "tagplader monteres på spær");
        if (length>600 && length<=960){
            PartsListLine p11 = new PartsListLine(getProduct(6),360,tagQuant, "tagplader monteres på spær");
            tagQuant=tagQuant*2;
        } else if(length>960){
            PartsListLine p12 = new PartsListLine(getProduct(6),600,tagQuant, "tagplader monteres på spær");
            tagQuant=tagQuant*2;
        }

        //tagskruer
        PartsListLine p13 = new PartsListLine(getProduct(7),0,tagQuant/4, "Skruer til tagplader");

        //hulbånd
        PartsListLine p14 = new PartsListLine(getProduct(8),0,2, "Til vindkryds på spær");

        //hulbåndskruer
        PartsListLine p15 = new PartsListLine(getProduct(9),0,spærQuantity, "Til montering af spær på rem");
        PartsListLine p16 = new PartsListLine(getProduct(10),0,spærQuantity, "Til montering af spær på rem");

        //skruer mv.
        PartsListLine p17 = new PartsListLine(getProduct(11),0,1, "Til montering af stern&vandbrædt");
        PartsListLine p18 = new PartsListLine(getProduct(12),0,spærQuantity/5, "Til montering af universalbeslag + hulbånd");

        PartsListLine p19 = new PartsListLine(getProduct(13),0,stolpeQuantity*3, "Til montering af rem på stolper");
        PartsListLine p20 = new PartsListLine(getProduct(14),0,stolpeQuantity*2, "Til montering af rem på stolper");

        PartsListLine p21 = new PartsListLine(getProduct(15),0,2, "Til montering af yderste beklæding");
        PartsListLine p22 = new PartsListLine(getProduct(16),0,2, "Til montering af inderste beklædning");

        return partsList;
    }

    private static Product getProduct(int id) {//from db
        String productName = "";//get product name fra db
        int productPrice = 0;// get product price from db
        String productUnit = "";//get product unit fra db
        Product product = new Product(productName,productPrice,productUnit);

        return product;
    }

}