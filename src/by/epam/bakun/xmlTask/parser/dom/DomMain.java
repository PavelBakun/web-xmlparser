package by.epam.bakun.xmlTask.parser.dom;

public class DomMain {
    public static void main(String[] args) {
        BanksDomBuilder builder = new BanksDomBuilder();
        builder.buildListBanks("src/by/epam/bakun/xmlTask/data/banks.xml");
        System.out.println(builder.getBanks());
    }
}
