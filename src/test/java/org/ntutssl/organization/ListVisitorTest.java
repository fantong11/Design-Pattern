package org.ntutssl.organization;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ListVisitorTest {
    private ListVisitor lv;
    private String result;
    private Workforce individual;
    private Workforce team;
    private Workforce team2;
    private Workforce team3;

    @Before
    public void init() {
        lv = new ListVisitor();
        result = "";
        individual = new Individual("Nick", 24000, LocalDate.now(), true);
        team = new Team("Nick", LocalDate.now(), true);
        team2 = new Team("Danny", LocalDate.now(), false);
        team3 =new Team("Ian", LocalDate.now(), false);
    }

    @Test
    public void visitIndividualTest() {
        individual.accept(lv);
        result = lv.getResult();
        System.out.println(result);
        assertEquals(
            "Type:       Individual (in-house)\n" +
            "Name:       Nick\n" +
            "Salary:     24000\n" +
            "Start date: 2021-04-17\n"
            , result);
    }

    @Test
    public void visitTeamTest() {
        team.accept(lv);
        result = lv.getResult();
        System.out.println(result);
        assertEquals(
            "Type:       Team (in-house)\n" +
            "Name:       Nick\n" +
            "Start date: 2021-04-17\n"
            , result);
    }

    @Test
    public void visitComplexTest() {
        team.add(individual);
        team2.add(individual);
        team3.add(individual);
        team.add(team2);
        team.add(team3);

        team.accept(lv);
        result = lv.getResult();
        System.out.println(result);
        assertEquals(
            "Type:       Team (in-house)\n" +
            "Name:       Nick\n" +
            "Start date: 2021-04-17\n" +
            "  Type:       Individual (in-house)\n" +
            "  Name:       Nick\n" +
            "  Salary:     24000\n" +
            "  Start date: 2021-04-17\n" +
            "  Type:       Team (outsourcing)\n" +
            "  Name:       Danny\n" +
            "  Start date: 2021-04-17\n" +
            "    Type:       Individual (in-house)\n" +
            "    Name:       Nick\n" +
            "    Salary:     24000\n" +
            "    Start date: 2021-04-17\n" +
            "  Type:       Team (outsourcing)\n" +
            "  Name:       Ian\n" +
            "  Start date: 2021-04-17\n" +
            "    Type:       Individual (in-house)\n" +
            "    Name:       Nick\n" +
            "    Salary:     24000\n" +
            "    Start date: 2021-04-17\n"
            , result);
    }
}