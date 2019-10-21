package by.epam.bakun.xmlTask.parser.stax;

import by.epam.bakun.xmlTask.entity.Bank;
import by.epam.bakun.xmlTask.entity.Depositor;
import by.epam.bakun.xmlTask.entity.TypeOfDeposit;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BanksStaxBuilder {
    private static Logger logger = Logger.getLogger(BanksStaxBuilder.class.getName());
    private List<Bank> banks = new ArrayList<>();
    private XMLInputFactory inputFactory;

    public BanksStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public List<Bank> getBanks() {
        logger.debug("method getBanks()");
        return banks;
    }

    public void buildBanks(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            logger.debug("try to read xml file");
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
// StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals("bank")) {
                        logger.debug("create new bank");
                        Bank bank = buildBank(reader);
                        banks.add(bank);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            logger.debug("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            logger.debug("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.debug("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Bank buildBank(XMLStreamReader reader) throws XMLStreamException {
        Bank bank = null;
        logger.debug("execute method buildBank");
        List<TypeOfDeposit> depositList = new ArrayList<>();
        TypeOfDeposit typeOfDeposit = null;
        List<Depositor> depositors = new ArrayList<>();
        Depositor depositor = null;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "name":
                        bank = new Bank();
                        bank.setName(getXMLText(reader));
                        break;
                    case "country":
                        bank.setCountry(getXMLText(reader));
                        break;
                    case "typeOfDeposit":
                        typeOfDeposit = new TypeOfDeposit();
                        typeOfDeposit.setName(reader.getAttributeValue(null, "name"));
                        break;
                    case "depositor":
                        depositor = new Depositor();
                        depositor.setName(reader.getAttributeValue(null, "name"));
                        break;
                    case "account_id":
                        depositor.setAccount_id(getXMLText(reader));
                        break;
                    case "amountOnDeposit":
                        depositor.setAmountOnDeposit(Double.parseDouble(getXMLText(reader)));
                        break;
                    case "profitability":
                        depositor.setProfitability(Double.parseDouble(getXMLText(reader)));
                        break;
                    case "timeConstraints":
                        depositor.setDate(getXMLText(reader));
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "bank":
                        return bank;

                    case "typesOfDeposit":
                        bank.setListDeposits(depositList);
                        break;
                    case "typeOfDeposit":
                        depositList.add(typeOfDeposit);
                        typeOfDeposit = new TypeOfDeposit();
                        break;
                    case "depositors":
                        typeOfDeposit.setListDepositors(depositors);
                        depositors = new ArrayList<>();
                        break;
                    case "depositor":
                        depositors.add(depositor);
                        break;
                }
            }
        }
        return bank;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        logger.debug("execute method getXMLText");
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
