package by.epam.bakun.xmlTask.parser;

import by.epam.bakun.xmlTask.entity.Bank;
import by.epam.bakun.xmlTask.parser.sax.BanksSaxBuilder;
import by.epam.bakun.xmlTask.parser.stax.BanksStaxBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BanksSaxAndStaxBuilderTest {
    @Test(description = "compare list<Bank> craeted by Dom and Sax parser")
    public void testSaxAndStaxBuilder() {
        String fileName = "src/by/epam/bakun/xmlTask/data/banks.xml";
        BanksSaxBuilder saxBuilder = new BanksSaxBuilder();
        saxBuilder.buildBanks(fileName);
        List<Bank> actual = saxBuilder.getBanks();

        BanksStaxBuilder staxBuilder = new BanksStaxBuilder();
        staxBuilder.buildBanks(fileName);
        List<Bank> expected = staxBuilder.getBanks();

        Assert.assertEquals(actual, expected);
    }
}
