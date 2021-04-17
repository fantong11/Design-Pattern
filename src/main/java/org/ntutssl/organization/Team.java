package org.ntutssl.organization;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Team implements Workforce {
  private String name;
  private LocalDate startDate;
  private boolean isInHouse;
  private List<Workforce> workforces;

  public Team(String name, LocalDate startDate, boolean isInHouse) {
    this.name = name;
    this.startDate = startDate;
    this.isInHouse = isInHouse;
    this.workforces = new ArrayList<>();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public LocalDate getStartDate() {
    return this.startDate;
  }

  @Override
  public boolean isInHouse() {
    return this.isInHouse;
  }

  @Override
  public String toString(int indentCount) {
    String indent = "";
    for (int i = 0; i < indentCount; indent += " ", i++)
      ;
    String type = indent + "Type:\tTeam (" + (this.isInHouse == true ? "in-house" : "outsourcing") + ")\n";
    String name = indent + "Name:\t" + this.name + "\n";
    String startDate = indent + "Start date:\t" + this.startDate + "\n";

    return type + name + startDate;
  }

  @Override
  public <T> void accept(Visitor<T> visitor) {
    visitor.visitTeam(this);
  }

  @Override
  public void add(Workforce employee) {
    workforces.add(employee);
  }

  @Override
  public Iterator<Workforce> iterator() {
    return workforces.iterator();
  }

  @Override
  public int getSize() {
    return this.workforces.size();
  }
}