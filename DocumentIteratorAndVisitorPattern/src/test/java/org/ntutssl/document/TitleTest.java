package org.ntutssl.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TitleTest {
    Title title;

    @Before
    public void setUp() {
        title = new Title("test", 20);
    }

    @After
    public void tearDown() {
        title = null;
    }

    @Test
    public void getTextTest() {
        assertEquals("test", title.getText());
    }

    @Test
    public void addTest() {
        boolean thrown = false;
        try {
            title.add(new Article("topic", 10));
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void toStringTest() {
        assertEquals("Title\t\ttext: test\n\t\tsize: 20\n", title.toString());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getLevelTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: getLevel");
        title.getLevel();
    }

    @Test
    public void addExceptionTest() {
        expectedEx.expect(DocumentException.class);
        expectedEx.expectMessage("Invalid action: add");
        title.add(new Article("topic", 10));
    }
}
