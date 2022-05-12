package dat.startcode.model.services;

import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Product product;
    private static Order order;

    @BeforeAll
    public static void setUpClass() {
        order = new Order(null,600,720);

    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void calcPartsList() {
    }

    @Test
    void name() {
    }

}