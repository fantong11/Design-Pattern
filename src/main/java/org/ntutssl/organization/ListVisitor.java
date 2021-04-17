package org.ntutssl.organization;

import java.util.Iterator;

public class ListVisitor implements Visitor<String> {
  private String result;
  private int level;
  private int indentCount;

  public ListVisitor() {
    this.result = "";
    this.level = 0;
    this.indentCount = 2;
  }

  public void visitTeam(Team team) {
    this.result += team.toString(this.level * this.indentCount);
    this.level++;

    Iterator<Workforce> it = team.iterator();
    while (it.hasNext()) {
      it.next().accept(this);
    }
    this.level--;
  }

  public void visitIndividual(Individual individual) {
    this.result += individual.toString(this.level * this.indentCount);
    this.level++;
  }

  public String getResult() {
    return this.result;
  }
}