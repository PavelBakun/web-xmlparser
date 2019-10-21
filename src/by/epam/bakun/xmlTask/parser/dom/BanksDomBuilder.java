package by.epam.bakun.xmlTask.parser.dom;

import by.epam.bakun.xmlTask.entity.Bank;
import by.epam.bakun.xmlTask.entity.Depositor;
import by.epam.bakun.xmlTask.entity.TypeOfDeposit;
import by.epam.bakun.xmlTask.parser.stax.BanksStaxBuilder;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BanksDomBuilder {
    private static Logger logger = Logger.getLogger(BanksStaxBuilder.class.getName());
    private List<Bank> banks;
    private DocumentBuilder docBuilder;

    public BanksDomBuilder() {
        this.banks = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            logger.debug("try to create new DocumentBuilder");
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Exception configuration of parser: " + e);
        }
    }

    public void buildListBanks(String fileName) {
        logger.debug("execute method buildListBanks");
        Document doc = null;
        try {
            logger.debug("try to parse file");
            doc = docBuilder.parse(fileName);
            this.banks = setListBanks(doc.getDocumentElement().getChildNodes());
        } catch (IOException e) {
            logger.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            logger.error("Parsing failure: " + e);
        }
    }

    public List<Bank> getBanks() {
        return banks;
    }

    private static List<Bank> setListBanks(NodeList nodeList) {
        logger.debug("execute method setListBanks");
        List<Bank> banks = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                Bank bank = new Bank();
                setElementOfBank(bank, nodeList.item(i).getChildNodes());
                banks.add(bank);
            }
        }
        return banks;
    }

    private static void setElementOfBank(Bank bank, NodeList nodeList) {
        logger.debug("execute method setElementOfBank");
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                String tagName = ((Element) nodeList.item(i)).getTagName();
                switch (tagName) {
                    case ("name"):
                        bank.setName(nodeList.item(i).getFirstChild().getNodeValue());
                        break;
                    case ("country"):
                        bank.setCountry(nodeList.item(i).getFirstChild().getNodeValue());
                        break;
                    case ("typesOfDeposit"):
                        List<TypeOfDeposit> depositList = new ArrayList<>();
                        setElementOfListDeposit(depositList, nodeList.item(i).getChildNodes());
                        bank.setListDeposits(depositList);
                        break;
                }
            }
        }
    }

    private static void setElementOfListDeposit(List<TypeOfDeposit> depositList, NodeList nodeList) {
        logger.debug("execute method setElementOfListDeposit");
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                TypeOfDeposit type = new TypeOfDeposit();
                type.setName(((Element) nodeList.item(i)).getAttribute("name").trim());
                setElementOfType(type, nodeList.item(i).getChildNodes());
                depositList.add(type);
            }
        }
    }

    private static void setElementOfType(TypeOfDeposit type, NodeList nodeList) {
        logger.debug("execute method setElementOfType");
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                ((Element) nodeList.item(i)).getTagName();
                NodeList nodeDepositors = nodeList.item(i).getChildNodes(); //get tag "depositors"
                List<Depositor> list = new ArrayList<>();
                for (int j = 0; j < nodeDepositors.getLength(); j++) {
                    if (nodeDepositors.item(j) instanceof Element) {
                        Depositor depositor = new Depositor();
                        depositor.setName(((Element) nodeDepositors.item(j)).getAttribute("name").trim());
                        setElementOfDepositor(depositor, nodeDepositors.item(j).getChildNodes());
                        list.add(depositor);
                    }
                }
                type.setListDepositors(list);
            }
        }
    }

    private static void setElementOfDepositor(Depositor depositor, NodeList nodeList) {
        logger.debug("execute method setElementOfDepositor");
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                String tagName = ((Element) nodeList.item(i)).getTagName();
                switch (tagName) {
                    case ("account_id"):
                        depositor.setAccount_id(nodeList.item(i).getFirstChild().getNodeValue());
                        break;
                    case ("amountOnDeposit"):
                        depositor.setAmountOnDeposit(Double.parseDouble(nodeList.item(i).getFirstChild().getNodeValue()));
                        break;
                    case ("profitability"):
                        depositor.setProfitability(Double.parseDouble(nodeList.item(i).getFirstChild().getNodeValue()));
                        break;
                    case ("timeConstraints"):
                        depositor.setDate(nodeList.item(i).getFirstChild().getNodeValue());
                        break;
                }
            }
        }
    }
}
