package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void init() {
        shoppingCart = new ShoppingCart();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void reset() {
        System.setOut(originalOut);
    }

    @Test
    public void addGoodsEventTest() {
        shoppingCart.onEvent(new GoodsEvent(EventType.ADD_TO_CART, new Collection(1, "collection", "desc"), 1));
        shoppingCart.onEvent(new GoodsEvent(EventType.ADD_TO_CART, new Merchandise(2, "merchandise", "desc", 100), 1));
        shoppingCart.onEvent(new StringEvent(EventType.LIST_CART, ""));

        String testOutput = ""; 
        testOutput += "================================================================================\n";
        testOutput += "ID  name                  description                             price   count \n";
        testOutput += "--------------------------------------------------------------------------------\n";
        testOutput += "1   collection            desc                                    0.0     1     \n";
        testOutput += "2   merchandise           desc                                    100.0   1     \n";
        testOutput += "================================================================================\n";
        assertEquals(testOutput, outContent.toString());
    }

    @Test
    public void emptyShoppingCartTest() {
        shoppingCart.onEvent(new StringEvent(EventType.LIST_CART, ""));
        assertEquals("Your shopping cart is empty\n", outContent.toString());
    }
}
