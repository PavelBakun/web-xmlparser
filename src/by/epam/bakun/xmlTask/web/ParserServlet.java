package by.epam.bakun.xmlTask.web;

import by.epam.bakun.xmlTask.entity.Bank;
import by.epam.bakun.xmlTask.parser.dom.BanksDomBuilder;
import by.epam.bakun.xmlTask.parser.sax.BanksSaxBuilder;
import by.epam.bakun.xmlTask.parser.stax.BanksStaxBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ParserServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(BanksStaxBuilder.class.getName());

    private List<Bank> banks;
    private String attribute;


    @Override
    public void init() throws ServletException {
        super.init();
        BasicConfigurator.configure();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("doGet with parametres: " + request + response);
        request.setCharacterEncoding("UTF-8");
        String fileName = "src/by/epam/bakun/xmlTask/data/banks.xml";
        String pathJSP = "/WEB-INF/jsp/list.jsp";
        this.attribute = request.getParameter("parser");
        switch (attribute) {
            case ("sax"):
                BanksSaxBuilder builder = new BanksSaxBuilder();
                builder.buildBanks(fileName);
                banks = builder.getBanks();
                parserHelp(banks, request, response, attribute, pathJSP);
                break;
            case ("dom"):
                BanksDomBuilder builderDom = new BanksDomBuilder();
                builderDom.buildListBanks(fileName);
                banks = builderDom.getBanks();
                parserHelp(banks, request, response, attribute, pathJSP);
                break;
            case ("stax"):
                BanksStaxBuilder builderStax = new BanksStaxBuilder();
                builderStax.buildBanks(fileName);
                banks = builderStax.getBanks();
                parserHelp(banks, request, response, attribute, pathJSP);
                break;
        }
    }
    private void parserHelp(List<Bank> banks, HttpServletRequest request, HttpServletResponse response,
                            String attribute, String pathJSP)  throws ServletException, IOException {
        request.setAttribute("parser", banks);
        request.setAttribute("typeParser", attribute);
        request.getRequestDispatcher(pathJSP).forward(request, response);
    }
}
