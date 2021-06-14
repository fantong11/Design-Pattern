package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private Shop shop;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @Before
    public void init() {
        shop = new Shop();
        shoppingCart = new ShoppingCart();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void reset() {
        EventManager.getInstance().reset();
        System.setIn(originalIn);
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

    @Test
    public void payTest() {
        shop.onEvent(new GoodsEvent(EventType.REPLENISH, new Collection(1, "collection", "desc"), 1));
        shop.onEvent(new GoodsEvent(EventType.REPLENISH, new Merchandise(2, "merchandise", "desc", 100), 1));
        shoppingCart.onEvent(new GoodsEvent(EventType.ADD_TO_CART, new Collection(1, "collection", "desc"), 1));
        shoppingCart.onEvent(new GoodsEvent(EventType.ADD_TO_CART, new Merchandise(2, "merchandise", "desc", 100), 1));
        new PayByCreditCardStrategy();

        final String cardNumber = "1234567812345678\n";
        final String expiredDate = "10/22\n";
        final String cvv = "123\n";
        final String input = cardNumber + expiredDate + cvv;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        shoppingCart.onEvent(new StringEvent(EventType.PAY, ""));

        String[] outputList = outContent.toString().split("\n");
        assertEquals("Pay successfully!", outputList[outputList.length - 1]);
    }

    @Test
    public void printReceiptTest() {
        shop.onEvent(new GoodsEvent(EventType.REPLENISH, new Collection(1, "collection", "desc"), 1));
        shop.onEvent(new GoodsEvent(EventType.REPLENISH, new Merchandise(2, "merchandise", "desc", 100), 1));
        shoppingCart.onEvent(new GoodsEvent(EventType.ADD_TO_CART, new Collection(1, "collection", "desc"), 1));
        shoppingCart.onEvent(new GoodsEvent(EventType.ADD_TO_CART, new Merchandise(2, "merchandise", "desc", 100), 1));
        new PayByCreditCardStrategy();

        final String cardNumber = "1234567812345678\n";
        final String expiredDate = "10/22\n";
        final String cvv = "123\n";
        final String input = cardNumber + expiredDate + cvv;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        shoppingCart.onEvent(new StringEvent(EventType.PAY, ""));

        String testOutput = ""; 
        testOutput += "================================================================================\n";
        testOutput += "Receipt:\n";
        testOutput += "name                                    price     count     \n";
        testOutput += "collection                              $0.0      1         \n";
        testOutput += "merchandise                             $100.0    1         \n";
        testOutput += "--------------------------------------------------------------------------------\n";
        testOutput += "Total Price: $90.00\n";
        testOutput += "================================================================================\n";
        assertEquals(testOutput, outContent.toString().substring(outContent.toString().indexOf("="), outContent.toString().lastIndexOf("=")+2));

    }
}
