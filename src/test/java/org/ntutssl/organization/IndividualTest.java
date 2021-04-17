package org.ntutssl.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IndividualTest {
    private Individual individual;

    @Before
    public void init() {
        this.individual = new Individual("Nick", 24000, LocalDate.now(), true);
    }

    @Test
    public void getNameTest() {
        assertEquals("Nick", individual.getName());
    }

    @Test
    public void getSalaryTest() {
        assertEquals(24000, individual.getSalary());
    }

    @Test
    public void getStartDateTest() {
        assertEquals(LocalDate.now(), individual.getStartDate());
    }

    @Test
    public void isInHouseTest() {
        assertTrue(individual.isInHouse());
    }
    
    @Test
    public void toStringTest() {
        // assertEquals("Article\t\ttopic: test\n\t\tlevel: 5\n", team.toString(1));
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void lowSalaryTest() {
        expectedEx.expect(OrganizationException.class);
        expectedEx.expectMessage("Salary cannot smaller than 24000");
        new Individual("Danny", 23000, LocalDate.now(), true);
    }

    @Test
    public void addExceptionTest() {
        expectedEx.expect(OrganizationException.class);
        expectedEx.expectMessage("Invalid action: add");
        individual.add(new Team("Danny", LocalDate.now(), true));
    }

    @Test
    public void getSizeExceptionTest() {
        expectedEx.expect(OrganizationException.class);
        expectedEx.expectMessage("Invalid action: getSize");
        individual.getSize();
    }

}