package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FixedDiscountDecoratorTest {
    private FixedDiscountDecorator fixedDiscountDecorator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Before
    public void init() {
        fixedDiscountDecorator = new FixedDiscountDecorator(new Merchandise(1, "merchandise", "desc", 100), 20);
    }

    @Test
    public void getIdTest() {
        assertEquals(1, fixedDiscountDecorator.id());
    }

    @Test
    public void getNameTest() {
        assertEquals("merchandise", fixedDiscountDecorator.name());
    }

    @Test
    public void getDescTest() {
        assertEquals("desc", fixedDiscountDecorator.description());
    }

    @Test
    public void addExceptionTest() {
        exception.expect(ShopException.class);
        fixedDiscountDecorator.add(new Collection(1, "collection", "desc"));
    }

    @Test
    public void discountMustSmallerThanPriceTest() {
        exception.expect(ShopException.class);
        new FixedDiscountDecorator(new Merchandise(1, "merchandise", "desc", 100), 101);
    }

    @Test
    public void discountEqualToPriceTest() {
        assertEquals(80, fixedDiscountDecorator.price(), 0.1);
    }
}
