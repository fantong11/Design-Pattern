package org.ntutssl.organization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class FindVisitor implements Visitor<List<Workforce>> {
  private Function<Workforce, Boolean> condition;
  private String orderBy;
  private List<Workforce> result;

  public FindVisitor(Function<Workforce, Boolean> condition, String orderBy) {
    this.condition = condition;
    this.orderBy = orderBy;
    this.result = new ArrayList<>();
  }

  public void visitTeam(Team team) {
    if (condition.apply(team)) {
      this.result.add(team);
    }

    Iterator<Workforce> it = team.iterator();
    while (it.hasNext()) {
      it.next().accept(this);
    }
  }

  public void visitIndividual(Individual individual) {
    if (condition.apply(individual)) {
      this.result.add(individual);
    }
  }

  public List<Workforce> getResult() {
    return this.result;
  }
}
