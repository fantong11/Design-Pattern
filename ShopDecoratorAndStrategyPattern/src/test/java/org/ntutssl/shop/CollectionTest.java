package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CollectionTest {
    private Goods collection;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        collection = new Collection(1, "bag", "desc");
    }

    @Test
    public void getIdTest() {
        assertEquals(1, collection.id());
    }

    @Test
    public void getNameTest() {
        assertEquals("bag", collection.name());
    }

    @Test
    public void getDescTest() {
        assertEquals("desc", collection.description());
    }

    @Test
    public void addSinglePrimitiveTest() {
        collection.add(new Merchandise(1, "merchandise", "desc", 100));

        Iterator<Goods> it = collection.iterator();
        assertEquals("merchandise", it.next().name());
    }

    @Test
    public void addMultiPrimitiveTest() {
        collection.add(new Merchandise(1, "merchandise1", "desc1", 100));
        collection.add(new Merchandise(1, "merchandise2", "desc2", 200));
        collection.add(new Merchandise(1, "merchandise3", "desc3", 300));

        Iterator<Goods> it = collection.iterator();
        assertEquals("merchandise1", it.next().name());
        assertEquals("merchandise2", it.next().name());
        assertEquals("merchandise3", it.next().name());
    }

    @Test
    public void addSingleCollectionTest() {
        collection.add(new Collection(1, "innerCollection", "desc"));

        Iterator<Goods> it = collection.iterator();
        assertEquals("innerCollection", it.next().name());
    }

    @Test
    public void addMultiCollectionTest() {
        collection.add(new Collection(1, "innerCollection1", "desc1"));
        collection.add(new Collection(2, "innerCollection2", "desc2"));
        collection.add(new Collection(3, "innerCollection3", "desc3"));

        Iterator<Goods> it = collection.iterator();
        assertEquals("innerCollection1", it.next().name());
        assertEquals("innerCollection2", it.next().name());
        assertEquals("innerCollection3", it.next().name());
    }

    @Test
    public void addCollectionAndMerchandiseTest() {
        collection.add(new Collection(1, "innerCollection1", "desc1"));
        collection.add(new Merchandise(2, "merchandise", "desc2", 100));
        collection.add(new Collection(3, "innerCollection2", "desc2"));

        Iterator<Goods> it = collection.iterator();
        assertEquals("innerCollection1", it.next().name());
        assertEquals("merchandise", it.next().name());
        assertEquals("innerCollection2", it.next().name());
    }

    @Test
    public void addNestTest() {
        collection.add(new Collection(1, "innerCollection1", "desc1"));
        Iterator<Goods> it = collection.iterator();
        it.next().add(new Merchandise(1, "merchandise", "desc1", 100));

        Iterator<Goods> itForTest1 = collection.iterator();
        Goods goodsForTest = itForTest1.next();
        assertEquals("innerCollection1", goodsForTest.name());
        assertEquals("merchandise", goodsForTest.iterator().next().name());
    }

    @Test
    public void calIntegerPriceWithMerchandiseTest() {
        collection.add(new Merchandise(1, "merchandise1", "desc1", 100));
        collection.add(new Merchandise(1, "merchandise2", "desc2", 200));
        collection.add(new Merchandise(1, "merchandise3", "desc3", 300));

        assertEquals(600.0, collection.price(), 0.1);
    }

    @Test
    public void calDoublePriceWithMerchandiseTest() {
        collection.add(new Merchandise(1, "merchandise1", "desc1", 100.52));
        collection.add(new Merchandise(1, "merchandise2", "desc2", 200.41));
        collection.add(new Merchandise(1, "merchandise3", "desc3", 300.89));

        assertEquals(601.82, collection.price(), 0.1);
    }

    @Test
    public void calPriceWithCollectionTest() {
        collection.add(new Collection(1, "innerCollection1", "desc1"));
        Iterator<Goods> it = collection.iterator();
        it.next().add(new Merchandise(1, "merchandise", "desc1", 100));

        assertEquals(100, collection.price(), 0.1);
    }

    @Test
    public void calPriceWithSigleCollectionTest() {
        collection.add(new Collection(1, "innerCollection1", "desc1"));
        assertEquals(0, collection.price(), 0.1);
    }

    @Test
    public void invalidIdTest() {
        exception.expect(ShopException.class);
        new Collection(-5, "collection", "desc");
    }
}
