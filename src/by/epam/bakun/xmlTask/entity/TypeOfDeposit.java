package by.epam.bakun.xmlTask.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TypeOfDeposit {
    private String name;
    private List<Depositor> listDepositors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Depositor> getListDepositors() {
        return listDepositors;
    }

    public void setListDepositors(List<Depositor> listDepositors) {
        this.listDepositors = listDepositors;
    }

    public void addDepositor(Depositor depositor) {
        listDepositors.add(depositor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfDeposit that = (TypeOfDeposit) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(listDepositors, that.listDepositors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, listDepositors);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
              s.append("\n").append(this.name).append("\n");
        for (Depositor depositor : listDepositors) {
            s.append(s).append(depositor);
        }
        return s.toString();
    }
}
