package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EventManagerTest {
    private EventManager eventManager;

    @Before
    public void init() {
        eventManager = EventManager.getInstance();
    }

    @Test
    public void publishSubscribeTest() {
        class TestListener implements EventListener {
            public boolean triggered = false;

            @Override
            public void onEvent(Event event) {
                triggered = event.type() == EventType.LIST_SHOP;
            }
            
        }

        TestListener test = new TestListener();

        eventManager.subscribe(EventType.LIST_SHOP, test);
        assertFalse(test.triggered);

        eventManager.publish(new StringEvent(EventType.LIST_SHOP, "test"));
        assertTrue(test.triggered);
    }

    @Test
    public void uniqueInstanceTest() {
        EventManager anotherEventManager = EventManager.getInstance();
        assertEquals(anotherEventManager, eventManager);
    }

    @Test
    public void bulletinClearTest() {
        class TestListener implements EventListener {
            public boolean triggered = false;

            @Override
            public void onEvent(Event event) {
                triggered = event.type() == EventType.LIST_SHOP;
            }
            
        }

        TestListener test = new TestListener();

        eventManager.subscribe(EventType.LIST_SHOP, test);
        assertFalse(test.triggered);

        eventManager.reset();
        
        eventManager.publish(new StringEvent(EventType.LIST_SHOP, "test"));
        assertFalse(test.triggered);
    }
}
