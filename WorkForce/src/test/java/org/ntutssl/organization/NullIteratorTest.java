package org.ntutssl.organization;

import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NullIteratorTest {
    private Individual individual;

    @Before
    public void setUp() {
        individual = new Individual("Danny", 25000, LocalDate.now(), true);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void individualIteratorNextExceptionTest() {
        expectedEx.expect(NoSuchElementException.class);
        Iterator<Workforce> it = individual.iterator();
        it.next();
    }

    @Test
    public void individualIteratorHasNextExceptionTest() {
        Iterator<Workforce> it = individual.iterator();
        assertFalse(it.hasNext());
    }
}
