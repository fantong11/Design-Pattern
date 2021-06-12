package org.ntutssl.shop;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class GoodsBuilderTest {
    private GoodsBuilder builder;

    @Before
    public void init() {
        builder = new GoodsBuilder();
    }

    @Test
    public void buildSingleMerchandiseTest() {
        builder.buildMerchandise(1, "merchandise", "desc", 100);
        assertEquals("merchandise", builder.getResult().name());
    }

    @Test
    public void buildMultiMerchandiseTest() {
        builder.buildMerchandise(1, "merchandise1", "desc1", 100);
        builder.buildMerchandise(2, "merchandise2", "desc2", 200);
        builder.buildMerchandise(3, "merchandise3", "desc3", 300);
        assertEquals("merchandise3", builder.getResult().name());
    }

    @Test
    public void buildSingleCollectionTest() {
        builder.startBuildCollection(1, "collection", "desc");
        builder.endBuildCollection();
        assertEquals("collection", builder.getResult().name());
    }

    @Test
    public void buildMultiCollectionTest() {
        builder.startBuildCollection(1, "collection1", "desc1");
        builder.startBuildCollection(2, "collection2", "desc2");
        builder.startBuildCollection(3, "collection3", "desc3");
        builder.endBuildCollection();
        builder.endBuildCollection();
        builder.endBuildCollection();

        assertEquals("collection1", builder.getResult().name());

        Iterator<Goods> it1 = builder.getResult().iterator();
        Goods collectionForTest1 = it1.next();
        assertEquals("collection2", collectionForTest1.name());

        Iterator<Goods> it2 = collectionForTest1.iterator();
        Goods collectionForTest2 = it2.next();
        assertEquals("collection3", collectionForTest2.name());
    }

    @Test
    public void buildNestTest() {
        builder.startBuildCollection(1, "collection1", "desc1");
        builder.buildMerchandise(1, "merchandise1", "desc1", 100);
        builder.startBuildCollection(2, "collection2", "desc2");
        builder.buildMerchandise(2, "merchandise2", "desc2", 200);
        builder.endBuildCollection();
        builder.endBuildCollection();

        assertEquals("collection1", builder.getResult().name());
        Iterator<Goods> it1 = builder.getResult().iterator();
        assertEquals("merchandise1", it1.next().name());
        Goods collectionForTest1 = it1.next();
        assertEquals("collection2", collectionForTest1.name());
        Iterator<Goods> it2 = collectionForTest1.iterator();
        assertEquals("merchandise2", it2.next().name());
    }
}
