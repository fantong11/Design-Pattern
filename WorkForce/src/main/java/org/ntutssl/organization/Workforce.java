package org.ntutssl.organization;

import java.time.LocalDate;
import java.util.Iterator;

public interface Workforce {
    default public void add(Workforce employee) {
        throw new OrganizationException("Invalid action: add");
    }

    default public int getSize() {
        throw new OrganizationException("Invalid action: getSize");
    }

    default public Iterator<Workforce> iterator() {
        return new NullIterator();
    }

    default public int getSalary() {
        throw new OrganizationException("Invalid action: getSalary");
    }

    public String getName();

    public LocalDate getStartDate();

    public boolean isInHouse();

    public <T> void accept(Visitor<T> visitor);

    public String toString(int indentCount);
}