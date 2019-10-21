package by.epam.bakun.xmlTask.parser.sax;

import by.epam.bakun.xmlTask.entity.Bank;
import by.epam.bakun.xmlTask.entity.Depositor;
import by.epam.bakun.xmlTask.entity.TypeOfDeposit;
import by.epam.bakun.xmlTask.parser.stax.BanksStaxBuilder;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class BankHandler extends DefaultHandler {

    /*this block of fields - for creating object of class "Bank"*/
    private static Logger logger = Logger.getLogger(BanksStaxBuilder.class.getName());
    private Depositor currentDepositor;
    private List<Depositor> currentDepositorsList;
    private TypeOfDeposit currentType;
    private List<TypeOfDeposit> currentTypesList;
    private List<Bank> banks;
    private Bank currentBank = null;
    private String element; // value of localName tags

    public BankHandler() {
        banks = new ArrayList<Bank>();
    }

    public List<Bank> getBanks() {
        return banks;
    }

    @Override
    public void startDocument() {
        //System.out.println("Parsing started!");
    }

    @Override
    public void endDocument() {
        //System.out.println("Parsing ended!");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        logger.debug("execute method startElement " + localName);
        element = localName;
        switch (localName) {
            case "bank": {
                currentBank = new Bank();
                break;
            }
            case "typesOfDeposit":
                currentTypesList = new ArrayList<>();
                break;
            case "typeOfDeposit":
                currentType = new TypeOfDeposit();
                currentType.setName(attrs.getValue(0));
                break;
            case "depositors":
                currentDepositorsList = new ArrayList<>();
                break;
            case "depositor":
                currentDepositor = new Depositor();
                currentDepositor.setName(attrs.getValue(0));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        logger.debug("execute method endElement " + localName);
        switch (localName) {
            case "bank": {
                banks.add(currentBank);//add object of class "Bank" in list
                break;
            }
            case "typesOfDeposit":
                currentBank.setListDeposits(currentTypesList);//
                break;
            case "typeOfDeposit":
                currentTypesList.add(currentType);
                break;
            case "depositors":
                currentType.setListDepositors(currentDepositorsList);
                break;
            case "depositor":
                currentDepositorsList.add(currentDepositor);
                break;
        }
    }

    public void characters(char[] ch, int start, int length) {
        logger.debug("execute method charactrs " + ch);
        String s = new String(ch, start, length).trim(); //get value of element, delete all spaces(it"s important!)
        if (!s.equals("")) { //checked value 's' for empty String
            switch (element) {
                case "name":
                    currentBank.setName(s);
                    break;
                case "country":
                    currentBank.setCountry(s);
                    break;
                case "account_id":
                    currentDepositor.setAccount_id(s);
                    break;
                case "amountOnDeposit":
                    double amount = Double.parseDouble(s);
                    currentDepositor.setAmountOnDeposit(amount);
                    break;
                case "profitability":
                    double profit = Double.parseDouble(s);
                    currentDepositor.setProfitability(profit);
                    break;
                case "timeConstraints":
                    currentDepositor.setDate(s);
                    break;
                default:
                    break;
            }
        }
    }
}