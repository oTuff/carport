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

        //widht & length check todo: make own method
        if(width>720){
            width2=width/2;
            width=width2;
            //add extra spærrem og stolper i midten
        }

        //splitcheck
//        if(!(width2 ==0))


        //sternbræder
            //fix unit.
            // fix description.
        PartsListLine p = new PartsListLine(getProduct(1),width,2, "stk", "understernbrædder til for & bag ende");
        PartsListLine p2 = new PartsListLine(getProduct(1),length,2, "ting", "understernbrædder til siderne");

        PartsListLine p3 = new PartsListLine(getProduct(2),width,1, "ting", "oversternbrædde til forenden");
        PartsListLine p4 = new PartsListLine(getProduct(2),length,2, "ting", "oversternbrædde til siderne");

        //spærtræ

        //stolpe

        //vandbræt

        //tagplader

        //tagskruer

        //hulbånd

        //hulbåndskruer

        //beslagskruer

        //bræddebolt

        return partsList;
    }

    private static Product getProduct(int id) {//from db
        String productName = "";//get product name fra db
        int productPrice = 0;// get product price from db
        Product product = new Product(productName,productPrice);

        return product;
    }

}