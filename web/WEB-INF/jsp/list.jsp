<%@ page import="by.epam.bakun.xmlTask.entity.Bank" %>
<%@ page import="java.util.List" %>
<%@ page import="by.epam.bakun.xmlTask.parser.sax.BanksSaxBuilder" %>
<%@ page import="by.epam.bakun.xmlTask.entity.TypeOfDeposit" %>
<%@ page import="by.epam.bakun.xmlTask.entity.Depositor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Banks</title>
</head>
<body>
<table border="1" width="1200" cellpadding="10" cellspacing="0">
    <caption><b>Banks description by <%=request.getAttribute("typeParser") + " parser"%></b></caption>
    <thead bgcolor ="#deb887" title="Шапка">
    <tr>
        <th>Depositor name</th>
        <th>Account id</th>
        <th>Amount On Deposit</th>
        <th>Profitability</th>
        <th>TimeConstraints</th>
        <th>TypeOfDeposit</th>
    </tr>
    </thead>
    <%
        for (Bank bank: (List<Bank>) request.getAttribute("parser")) {
     %>
    <tbody align="center" bgcolor ="#faebd7" title="Descriptions of Bank">
    <tr>
        <th colspan="6">Bank name="<%=bank.getName()%>", Country="<%=bank.getCountry()%>"</th>
    </tr>
    <tr>

    <tbody align="center" bgcolor ="#faebd7" title="Descriptions of Bank">
    <%
        List<TypeOfDeposit> depositList = bank.getListDeposits();
        for (TypeOfDeposit type: depositList) {
            List<Depositor> depositors = type.getListDepositors();
            for (Depositor depositor: depositors) {
    %>

    <tr>
        <th><%=depositor.getName()%></th>
        <td><%=depositor.getAccount_id()%></td>
        <td><%=depositor.getAmountOnDeposit()%></td>
        <td><%=depositor.getProfitability()%></td>
        <td><%=depositor.getDate()%></td>
        <td><%=type.getName()%></td>
    </tr>
        <%    }%>
    <%    }%>
    <%}%>
    </tbody>

</table>
</body>
</html>
