package by.epam.bakun.xmlTask.entity;

import java.util.Objects;

public class Depositor {

    private String name;
    private String account_id;
    private double amountOnDeposit;
    private double profitability;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public double getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(double amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depositor depositor = (Depositor) o;
        return Double.compare(depositor.amountOnDeposit, amountOnDeposit) == 0 &&
                Double.compare(depositor.profitability, profitability) == 0 &&
                Objects.equals(name, depositor.name) &&
                Objects.equals(account_id, depositor.account_id) &&
                Objects.equals(date, depositor.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, account_id, amountOnDeposit, profitability, date);
    }

    @Override
    public String toString() {
        return "Depositor{" +
                "name='" + name + '\'' +
                ", account_id='" + account_id + '\'' +
                ", amountOnDeposit=" + amountOnDeposit +
                ", profitability=" + profitability +
                ", date=" + date +
                '}';
    }
}
