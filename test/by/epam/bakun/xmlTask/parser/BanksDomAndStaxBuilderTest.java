package by.epam.bakun.xmlTask.parser;

import by.epam.bakun.xmlTask.entity.Bank;
import by.epam.bakun.xmlTask.parser.dom.BanksDomBuilder;
import by.epam.bakun.xmlTask.parser.stax.BanksStaxBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BanksDomAndStaxBuilderTest {
    @Test(description = "compare list<Bank> created by Dom and Stax parser")
    public void testDomAndStaxBuilder() {
        String fileName = "src/by/epam/bakun/xmlTask/data/banks.xml";
        BanksDomBuilder builder = new BanksDomBuilder();
        builder.buildListBanks(fileName);
        List<Bank> actual = builder.getBanks();

        BanksStaxBuilder staxBuilder = new BanksStaxBuilder();
        staxBuilder.buildBanks(fileName);
        List<Bank> expected = staxBuilder.getBanks();

        Assert.assertEquals(actual, expected);
    }
}