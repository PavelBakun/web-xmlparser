package by.epam.bakun.xmlTask.entity;

import java.util.List;
import java.util.Objects;

public class Bank {
    private String name;
    private String country;
    private List<TypeOfDeposit> listDeposits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<TypeOfDeposit> getListDeposits() {
        return listDeposits;
    }

    public void setListDeposits(List<TypeOfDeposit> listDeposits) {
        this.listDeposits = listDeposits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name) &&
                Objects.equals(country, bank.country) &&
                Objects.equals(listDeposits, bank.listDeposits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, listDeposits);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", listDeposits=" + listDeposits +
                '}';
    }
}