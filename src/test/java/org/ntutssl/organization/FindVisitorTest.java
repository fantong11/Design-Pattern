package org.ntutssl.organization;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

public class FindVisitorTest {
    private FindVisitor fv;
    private List<Workforce> result;
    private Workforce individual;
    private Workforce team;
    private Workforce team2;
    private Workforce team3;
    private Function<Workforce, Boolean> condition;

    @Before
    public void init() {
        result = new ArrayList<>();
        individual = new Individual("Nick", 24000, LocalDate.now(), true);
        team = new Team("Nick", LocalDate.now(), true);
        team2 = new Team("Danny", LocalDate.now(), false);
        team3 =new Team("Ian", LocalDate.now(), false);
        condition = (individual) -> {
            if (individual.getName().equals("Nick")) return Boolean.TRUE;
            return Boolean.FALSE;
        };
        fv = new FindVisitor(condition, "acs");
    }

    @Test
    public void visitIndividualTest() {
        individual.accept(fv);
        result = fv.getResult();
        assertEquals(1, result.size());
    }

    @Test
    public void visitTeamTest() {
        team.accept(fv);
        result = fv.getResult();
        assertEquals(1, result.size());
    }

    @Test
    public void visitComplexTest() {
        team.add(individual);
        team2.add(individual);
        team3.add(individual);
        team.add(team2);
        team.add(team3);

        team.accept(fv);
        result = fv.getResult();
        assertEquals(4, result.size());
    }
}
