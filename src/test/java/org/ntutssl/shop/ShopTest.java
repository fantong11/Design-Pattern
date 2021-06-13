package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShopTest {
    private Shop shop;
    private ShoppingCart shoppingCart;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void init() {
        shop = new Shop();
        shoppingCart = new ShoppingCart();
        System.setOut(new PrintStream(outContent));

    }

    @After
    public void reset() {
        System.setOut(originalOut);
    }

    @Test
    public void replenishTest() {
        shop.onEvent(new GoodsEvent(EventType.REPLENISH, new Collection(1, "collection", "desc"), 1));
        shop.onEvent(new GoodsEvent(EventType.REPLENISH, new Merchandise(1, "merchandise", "desc", 100), 1));
        shop.onEvent(new StringEvent(EventType.LIST_SHOP, ""));

        String testOutput = ""; 
        testOutput += "================================================================================\n";
        testOutput += "ID  name                  description                             price   count \n";
        testOutput += "--------------------------------------------------------------------------------\n";
        testOutput += "1   collection            desc                                    0.0     1     \n";
        testOutput += "1   merchandise           desc                                    100.0   1     \n";
        testOutput += "================================================================================\n";
        assertEquals(testOutput, outContent.toString());
    }

    @Test
    public void emptyShopTest() {
        shop.onEvent(new StringEvent(EventType.LIST_SHOP, ""));
        assertEquals("This shop does not sell anything.\n", outContent.toString());
    }

    @Test
    public void checkStockTest() {
        shop.onEvent(new GoodsEvent(EventType.REPLENISH, new Collection(1, "collection", "desc"), 1));
        shop.onEvent(new GoodsEvent(EventType.REPLENISH, new Merchandise(2, "merchandise", "desc", 100), 1));
        shop.onEvent(new GoodsEvent(EventType.CHECK_STOCK, new Merchandise(2, "merchandise", "desc", 100), 1));
        shop.onEvent(new StringEvent(EventType.LIST_SHOP, ""));

        String testOutput = ""; 
        testOutput += "================================================================================\n";
        testOutput += "ID  name                  description                             price   count \n";
        testOutput += "--------------------------------------------------------------------------------\n";
        testOutput += "1   collection            desc                                    0.0     1     \n";
        testOutput += "2   merchandise           desc                                    100.0   1     \n";
        testOutput += "================================================================================\n";
        assertEquals(testOutput, outContent.toString());

    }
}
