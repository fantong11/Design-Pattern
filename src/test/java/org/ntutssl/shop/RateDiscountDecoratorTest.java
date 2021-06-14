package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RateDiscountDecoratorTest {
    private RateDiscountDecorator rateDiscountDecorator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Before
    public void init() {
        rateDiscountDecorator = new RateDiscountDecorator(new Merchandise(1, "merchandise", "desc", 100), 0.5);
    }

    @Test
    public void getIdTest() {
        assertEquals(1, rateDiscountDecorator.id());
    }

    @Test
    public void getNameTest() {
        assertEquals("merchandise", rateDiscountDecorator.name());
    }

    @Test
    public void getDescTest() {
        assertEquals("desc", rateDiscountDecorator.description());
    }

    @Test
    public void addExceptionTest() {
        exception.expect(ShopException.class);
        rateDiscountDecorator.add(new Collection(1, "collection", "desc"));
    }

    @Test
    public void discountBiggerThanOneTest() {
        exception.expect(ShopException.class);
        new RateDiscountDecorator(new Merchandise(1, "merchandise", "desc", 100), 1.1);
    }

    @Test
    public void discountSmallerThanZeroTest() {
        exception.expect(ShopException.class);
        new RateDiscountDecorator(new Merchandise(1, "merchandise", "desc", 100), -0.1);
    }

    @Test
    public void discountEqualToPriceTest() {
        assertEquals(50, rateDiscountDecorator.price(), 0.1);
    }
}
