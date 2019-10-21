package by.epam.bakun.xmlTask.parser.stax;

public class StaxMain {
    public static void main(String[] args) {
        BanksStaxBuilder builder = new BanksStaxBuilder();
        builder.buildBanks("src/by/epam/bakun/xmlTask/data/banks.xml");
        System.out.println(builder.getBanks());
    }
}
