package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EventTest {
    private Event<Goods> goodsEvent;
    private Event<String> stringEvent;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        goodsEvent = new GoodsEvent(EventType.ADD_TO_CART, new Merchandise(1, "merchandise", "desc", 100), 1);
        stringEvent = new StringEvent(EventType.IMPORT_REPLENISH_LIST, "event");
    }

    @Test
    public void getGoodsEventCountTest() {
        assertEquals(1, goodsEvent.count());
    }

    @Test
    public void getGoodsEventTypeTest() {
        assertEquals(EventType.ADD_TO_CART, goodsEvent.type());
    }

    @Test
    public void getGoodsEventDataTest() {
        assertEquals("merchandise", goodsEvent.data().name());
    }

    @Test
    public void getStringEventCountTest() {
        exception.expect(ShopException.class);
        stringEvent.count();
    }

    @Test
    public void getStringEventTypeTest() {
        assertEquals(EventType.IMPORT_REPLENISH_LIST, stringEvent.type());
    }

    @Test
    public void getStringEventDataTest() {
        assertEquals("event", stringEvent.data());
    }
}
