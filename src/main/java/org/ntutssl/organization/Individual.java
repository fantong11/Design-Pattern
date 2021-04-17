package org.ntutssl.organization;

import java.time.LocalDate;

public class Individual implements Workforce {
  private String name;
  private int salary;
  private LocalDate startDate;
  private boolean isInHouse;

  public Individual(String name, int salary, LocalDate startDate, boolean isInHouse) {
    if (name.isEmpty() || name.equals("[\\W_]+") || name.equals("[\\D_]+")){
      throw new OrganizationException("Wrong name pattern");
    }
    if (salary < 24000) throw new OrganizationException("Salary cannot smaller than 24000");
    this.name = name;
    this.salary = salary;
    this.startDate = startDate;
    this.isInHouse = isInHouse;
  }

  public String getName() {
    return this.name;
  }

  public LocalDate getStartDate() {
    return this.startDate;
  }

  public boolean isInHouse() {
    return this.isInHouse;
  }

  public String toString(int indentCount) {
    String indent = "";
    for (int i = 0; i < indentCount; indent += " ", i++)
      ;
    String type = indent + "Type:\tTeam (" + (this.isInHouse == true ? "in-house" : "outsourced") + ")\n";
    String name = indent + "Name:\t" + this.name + "\n";
    String salary = indent + "Salary:\t" + this.salary + "\n";
    String startDate = indent + "Start date:\t" + this.startDate + "\n";

    return type + name + salary + startDate;
  }

  public <T> void accept(Visitor<T> visitor) {
    visitor.visitIndividual(this);
  }

  @Override
  public int getSalary() {
    return this.salary;
  }
}