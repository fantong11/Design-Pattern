package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MerchandiseTest {
    private Goods merchandise;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        merchandise = new Merchandise(1, "banana", "desc", 100);
    }

    @Test
    public void getIdTest() {
        assertEquals(1, merchandise.id());
    }

    @Test
    public void getNameTest() {
        assertEquals("banana", merchandise.name());
    }

    @Test
    public void getDescTest() {
        assertEquals("desc", merchandise.description());
    }

    @Test
    public void addExceptionTest() {
        exception.expect(ShopException.class);
        merchandise.add(new Collection(1, "collection", "desc"));
    }

    @Test
    public void iteratorHasNextTest() {
        assertFalse(merchandise.iterator().hasNext());
    }

    @Test
    public void iteratorNextExceptionTest() {

        exception.expect(NoSuchElementException.class);
        merchandise.iterator().next();
    }

    @Test
    public void invalidIdTest() {
        exception.expect(ShopException.class);
        new Merchandise(-5, "Merchandise", "desc", 100);
    }

    @Test
    public void invalidPriceTest() {
        exception.expect(ShopException.class);
        new Merchandise(5, "Merchandise", "desc", -100);
    }
}

