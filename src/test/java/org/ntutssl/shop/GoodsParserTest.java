package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GoodsParserTest {
    
    private GoodsParser goodsParser;
    private Shop shop;
    private ShoppingCart shoppingCart;
    private JsonObject merchandiseJson;
    private JsonObject collectionJson;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setup() {
        goodsParser = new GoodsParser();
        shop = new Shop();
        shoppingCart = new ShoppingCart();
        System.setOut(new PrintStream(outContent));

        String merchandiseJsonString = 
        "{" + 
           "\"type\": \"merchandise\"," +
           "\"id\": 1," +
           "\"name\": \"apple\"," +
           "\"description\": \"good apple\"," +
           "\"price\": 0.49," +
           "\"count\": 3" +
        "}";


        String collectionJsonString = 
        "{" +
        "    \"type\": \"collection\"," +
        "    \"id\": 2," +
        "    \"name\": \"banana bag\"," +
        "    \"description\": \"a bag of banana\"," +
        "    \"count\": 1," +
        "    \"contents\": [" +
        "        {" +
        "            \"type\": \"merchandise\"," +
        "            \"id\": 13," +
        "            \"name\": \"banana\"," +
        "            \"description\": \"big banana\"," +
        "            \"price\": 0.29" +
        "        }" +
        "    ]" +
        "}";
        merchandiseJson = new Gson().fromJson(merchandiseJsonString, JsonObject.class);
        collectionJson = new Gson().fromJson(collectionJsonString, JsonObject.class);
    }

    @After
    public void reset() {
        System.setOut(originalOut);
    }

    @Test
    public void importReplenishmentListTest() {
        goodsParser.onEvent(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
        shop.onEvent(new StringEvent(EventType.LIST_SHOP, ""));

        String testOutput = ""; 
        testOutput += "================================================================================\n";
        testOutput += "ID  name                  description                             price   count \n";
        testOutput += "--------------------------------------------------------------------------------\n";
        testOutput += "1   apple                 good apple                              0.49    100   \n";
        testOutput += "2   banana bag            a bag of banana                         0.29    30    \n";
        testOutput += "3   cat                   cute cat                                0.49    50    \n";
        testOutput += "4   dog                   a bag of Chihuahuas                     0.0     123   \n";
        testOutput += "5   egg                   cheap!                                  0.05    500   \n";
        testOutput += "6   fruit                 cdesc                                   6.48    300   \n";
        testOutput += "7   pencil                cheap pencil                            0.29    1000  \n";
        testOutput += "8   eraser                cheap eraser                            0.25    400   \n";
        testOutput += "9   pen                   cheat pen                               0.49    400   \n";
        testOutput += "10  stationery pack       stationery pack                         1.03    50    \n";
        testOutput += "11  MSI laptop            superb RGB light up your world          700.0   30    \n";
        testOutput += "12  MacBook Pro           Small chip. Giant leap.                 1299.0  10    \n";
        testOutput += "13  laptop pack           laptop pack                             799.98  5     \n";
        testOutput += "================================================================================\n";
        assertEquals(testOutput, outContent.toString());
    }

    @Test
    public void importShoppingCartListTest() {
        goodsParser.onEvent(new StringEvent(EventType.IMPORT_REPLENISH_LIST, "input/replenish_list.json"));
        goodsParser.onEvent(new StringEvent(EventType.IMPORT_SHOPPING_LIST, "input/shopping_list.json"));
        shoppingCart.onEvent(new StringEvent(EventType.LIST_CART, ""));

        String testOutput = ""; 
        testOutput += "================================================================================\n";
        testOutput += "ID  name                  description                             price   count \n";
        testOutput += "--------------------------------------------------------------------------------\n";
        testOutput += "1   apple                 good apple                              0.49    3     \n";
        testOutput += "2   banana bag            a bag of banana                         0.29    1     \n";
        testOutput += "3   cat                   cute cat                                0.49    2     \n";
        testOutput += "4   dog                   a bag of Chihuahuas                     0.0     2     \n";
        testOutput += "5   egg                   cheap!                                  0.05    4     \n";
        testOutput += "6   fruit                 cdesc                                   6.48    1     \n";
        testOutput += "================================================================================\n";
        assertEquals(testOutput, outContent.toString());
    }

    @Test
    public void merchandiseTest() {
        Goods result = goodsParser.parse(this.merchandiseJson);
        assertEquals("apple", result.name());
    }

    @Test
    public void CollectionTest() {
        Goods result = goodsParser.parse(this.collectionJson);
        assertEquals("banana bag", result.name());
    }

}
