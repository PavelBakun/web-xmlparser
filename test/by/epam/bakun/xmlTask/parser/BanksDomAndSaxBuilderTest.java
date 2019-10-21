package by.epam.bakun.xmlTask.parser;

import by.epam.bakun.xmlTask.entity.Bank;
import by.epam.bakun.xmlTask.parser.dom.BanksDomBuilder;
import by.epam.bakun.xmlTask.parser.sax.BanksSaxBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BanksDomAndSaxBuilderTest {

    @Test(description = "compare list<Bank> craeted by Dom and Sax parser")
    public void testDomAndSaxBuilder() {
        String fileName = "src/by/epam/bakun/xmlTask/data/banks.xml";
        BanksDomBuilder builder = new BanksDomBuilder();
        builder.buildListBanks(fileName);
        List<Bank> actual = builder.getBanks();

        BanksSaxBuilder saxBuilder = new BanksSaxBuilder();
        saxBuilder.buildBanks(fileName);
        List<Bank> expected = saxBuilder.getBanks();

        Assert.assertEquals(actual, expected);
    }
}