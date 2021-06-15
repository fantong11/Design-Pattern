package org.ntutssl.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TeamTest {
    private Team team;
    private Iterator<Workforce> it;

    @Before
    public void init() {
        it = null;
        team = new Team("Nick", LocalDate.now(), true);
    }

    @Test
    public void getNameTest() {
        assertEquals("Nick", team.getName());
    }

    @Test
    public void getStartDateTest() {
        assertEquals(LocalDate.now(), team.getStartDate());
    }

    @Test
    public void isInHouseTest() {
        assertTrue(team.isInHouse());
    }

    @Test
    public void addSinglePrimitiveWorkforceTest() {
        team.add(new Individual("John", 24000, LocalDate.now(), true));
        it = team.iterator();
        assertEquals("John", it.next().getName());
    }

    @Test
    public void addMultiPrimitiveWorkforcesTest() {
        team.add(new Individual("John", 24000, LocalDate.now(), true));
        team.add(new Individual("Danny", 30000, LocalDate.now(), true));
        it = team.iterator();
        assertEquals("John", it.next().getName());
        assertEquals("Danny", it.next().getName());
    }

    @Test
    public void addSingleTeamTest() {
        team.add(new Team("John", LocalDate.now(), true));
        it = team.iterator();
        assertEquals("John", it.next().getName());
    }

    @Test
    public void addMultiTeamsTest() {
        team.add(new Team("John", LocalDate.now(), true));
        team.add(new Team("Danny", LocalDate.now(), true));
        it = team.iterator();
        assertEquals("John", it.next().getName());
        assertEquals("Danny", it.next().getName());
    }

    @Test
    public void addArticleAndPrimitiveWorkforceTest() {
        team.add(new Team("John", LocalDate.now(), true));
        team.add(new Team("Danny", LocalDate.now(), true));
        team.add(new Individual("Ian", 30000, LocalDate.now(), true));
        it = team.iterator();
        assertEquals("John", it.next().getName());
        assertEquals("Danny", it.next().getName());
        assertEquals("Ian", it.next().getName());
    }

    @Test
    public void addPrimitiveWorkforceInTeamTest() {
        team.add(new Team("John", LocalDate.now(), true));
        it = team.iterator();
        it.next().add(new Individual("Ian", 30000, LocalDate.now(), true));
        Iterator<Workforce> it2 = team.iterator();
        assertEquals("Ian", it2.next().iterator().next().getName());
    }

    @Test
    public void toStringTest() {
        assertEquals(
            "  Type:       Team (in-house)\n" +
            "  Name:       Nick\n" +
            "  Start date: 2021-04-17\n"
            , team.toString(2));
    }

    @Test
    public void getSize() {
        team.add(new Individual("John", 24000, LocalDate.now(), true));
        team.add(new Individual("Danny", 30000, LocalDate.now(), true));
        assertEquals(2, team.getSize());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Test
    public void getSalaryExceptionTest() {
        expectedEx.expect(OrganizationException.class);
        expectedEx.expectMessage("Invalid action: getSalary");
        team.getSalary();
    }

    @Test
    public void nameIsEmptyString() {
        expectedEx.expect(OrganizationException.class);
        expectedEx.expectMessage("Wrong name pattern");
        new Team("", LocalDate.now(), true);
    }
}