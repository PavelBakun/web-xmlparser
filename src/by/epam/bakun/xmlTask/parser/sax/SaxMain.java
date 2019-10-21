package by.epam.bakun.xmlTask.parser.sax;

public class SaxMain {
    public static void main(String[] args) {
        BanksSaxBuilder builder = new BanksSaxBuilder();
        builder.buildBanks("src/by/epam/bakun/xmlTask/data/banks.xml");
        System.out.println(builder.getBanks());
    }
}
