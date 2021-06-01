package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.Before;
import org.junit.Test;

public class GoodsParserTest {
    private GoodsParser parser = new GoodsParser();
    private JsonObject merchandiseJson;
    private JsonObject collectionJson;
    private GoodsBuilder docBuilder = new GoodsBuilder();

    @Before
    public void setup() {
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

    @Test
    public void merchandiseTest() {

    }

    @Test
    public void test_buildCollection() {
        docBuilder.startBuildCollection(1, "a", "apple");
        docBuilder.endBuildCollection();
        Goods doc = docBuilder.getResult();
        assertEquals(1, doc.id());
        assertEquals("a", doc.name());
        assertEquals("apple", doc.description());
    }
}
