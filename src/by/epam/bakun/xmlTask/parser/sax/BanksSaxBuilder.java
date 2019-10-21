package by.epam.bakun.xmlTask.parser.sax;

import by.epam.bakun.xmlTask.entity.Bank;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class BanksSaxBuilder {
    private List<Bank> banks;
    private BankHandler bankHandler;
    private XMLReader reader;

    public BanksSaxBuilder() {
        bankHandler = new BankHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(bankHandler);
        } catch (SAXException e) {
            System.err.print("Exception of SAXParser" + e);
        }
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void buildBanks(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("Exception of SAXParser" + e);
        } catch (IOException e) {
            System.err.print("Exception of I/O stream" + e);
        }
        banks = bankHandler.getBanks();
    }
}