package dat.startcode.model.services;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.PartsListLine;
import dat.startcode.model.entities.Product;

import java.util.ArrayList;

public class Calculator {
    //what to return?? void/partslis array/int??
    public static ArrayList<PartsListLine> calcPartsList(Order order){
        ArrayList<PartsListLine> partsList = new ArrayList<>();
        //sternbræder


        //spærtræ

        //stolpe

        //vandbræt

        //tagplader

        //tagskruer

        //hulbånd

        //hulbåndskruer

        //beslagskruer

        //bræddebolt

    }

    private Product getProduct(int id){//from db
        String productName = "";//få product name fra db
        Product product = new Product();

    }

}
