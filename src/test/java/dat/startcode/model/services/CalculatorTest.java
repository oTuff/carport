package dat.startcode.model.services;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Product;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CalculatorTest {
    private static Order order;
    private static Order order2;
    private static ArrayList<Product> products;
    private static Calculator calculator;
    private static Calculator calculator2;

    @BeforeAll
    public static void setUpClass() {
        order = new Order(null, 600, 780);
        order2 = new Order(null, 600, 600);
        products = new ArrayList<>();
        calculator = new Calculator(order, products);
        calculator2 = new Calculator(order2, products);

        for (int i = 0; i < 16; i++) {
            products.add(new Product("product", i, "."));
        }

    }

    @BeforeEach
    void setUp() {
        calculator.calcPartsList();
        calculator2.calcPartsList();
    }

    @Test
    void numberOfPartsListLines() {
        Assertions.assertEquals(21, order.getPartsListLines().size());
        Assertions.assertEquals(20, order2.getPartsListLines().size());
    }

    @Test
    void calcRafterQuantity() {
        Assertions.assertEquals(15, calculator.getRafterQuantity());
        Assertions.assertEquals(12, calculator2.getRafterQuantity());
    }

    @Test
    void calcPosts() {
        Assertions.assertEquals(6, calculator.getPostQuantity());
        Assertions.assertEquals(4, calculator2.getPostQuantity());
    }

    @Test
    void splitCheck() {
        Assertions.assertEquals(4, calculator.getBoardQuantity());
        Assertions.assertEquals(2, calculator2.getBoardQuantity());

        Assertions.assertEquals(390, calculator.getBoardLength());
        Assertions.assertEquals(600, calculator2.getBoardLength());
    }

    @Test
    void calcRoofing() {
        Assertions.assertEquals(12, calculator.getRoofQuantity());
        Assertions.assertEquals(6, calculator2.getRoofQuantity());
    }

    @Test
    void calcPrice() {
        Assertions.assertEquals(180, calculator.calcLinePrice(order.getPartsListLines().get(5)));
        Assertions.assertEquals(14, calculator.calcLinePrice(order.getPartsListLines().get(12)));
    }
}