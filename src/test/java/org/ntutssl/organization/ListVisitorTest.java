package org.ntutssl.organization;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ListVisitorTest {
    private ListVisitor lv;
    private String result;
    private Workforce individual;
    private Workforce team;

    @Before
    public void init() {
        lv = new ListVisitor();
        result = "";
        individual = new Individual("Nick", 24000, LocalDate.now(), true);
        team = new Team("Nick", LocalDate.now(), true);
    }
    
    @Test
    public void visitIndividualTest() {
        individual.accept(lv);
        result = lv.getResult();
        System.out.println(result);
        // assertEquals("<p>design pattern</p>\n", result);
    }

    @Test
    public void visitTeamTest() {
        team.accept(lv);
        result = lv.getResult();
        System.out.println(result);
        // assertEquals("<p>design pattern</p>\n", result);
    }

    @Test
    public void visitComplexTest() {
        team.add(individual);
        team.accept(lv);
        result = lv.getResult();
        System.out.println(result);
    }
}