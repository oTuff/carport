package dat.startcode.model.services;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    private Product product;
    private static Order order;
    private static Order order2;
    private static Calculator calculator;
    private static Calculator calculator2;


    @BeforeAll
    public static void setUpClass() {
        order = new Order(null,600,720);
        order2 = new Order(null,600,500);
        calculator=new Calculator(order);
        calculator2=new Calculator(order2);
    }

    @BeforeEach
    void setUp() {
        calculator.calcPartsList();
        calculator2.calcPartsList();
    }

    @Test
    void calcPartsList() {
        System.out.println(order.getPartsListLines());

    }

    @Test
    void numberOfPartsListLines() {
        Assertions.assertEquals(21,order.getPartsListLines().size());
    }

    @Test
    void calcRafterQuantity() {
        Assertions.assertEquals(14,order.getPartsListLines().get(5).getQuantity());
    }

    @Test
    void calcPosts() {
        Assertions.assertEquals(6,order.getPartsListLines().get(6).getQuantity());
        Assertions.assertEquals(4,order2.getPartsListLines().get(6).getQuantity());
    }

    @Test
    void calcRoofing() {

    }

    @Test
    void getProduct() {

    }

    @Test
    void getDescription() {

    }
}