package org.ntutssl.organization;

import java.util.Iterator;

public class ListVisitor implements Visitor<String> {
  private String result;

  public ListVisitor() {
    this.result = "";
  }

  public void visitTeam(Team team) {
    this.result += team.toString();
  }

  public void visitIndividual(Individual individual) {
    this.result += individual.toString();
    
    Iterator<Workforce> it = individual.iterator();
    while (it.hasNext()) {
      it.next().accept(this);
    }
  }

  public String getResult() {
    return this.result;
  }
}