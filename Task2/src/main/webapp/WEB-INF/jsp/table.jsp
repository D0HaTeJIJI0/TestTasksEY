<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%--<html lang="en">--%>
<head>
    <meta charset="UTF-8">
    <title>Account</title>
</head>
<body>
    <table align="center" border="1" style="text-align: center" >
        <caption>Оборотная ведомость по балансовым счетам</caption>
        <tr>
            <th>Б/сч</th>
            <th>Входящий актив</th>
            <th>Входящий пассив</th>
            <th>Дебет</th>
            <th>Кредит</th>
            <th>Исходящий актив</th>
            <th>Исходящий пассив</th>
        </tr>
        <c:set var="counter" value="0"/>
        <c:forEach items="${classes}" var="classAccount">
            <%
                String counterStr = (String) pageContext.getAttribute("counter");
                int counter = Integer.parseInt(counterStr);
                pageContext.setAttribute("counter", new Integer(++counter).toString());
            %>
            <tr>
                <td colspan="7" style="font-weight: bold"><c:out value="КЛАСС ${counter} ${classAccount.name}"></c:out></td>
            </tr>
            <c:forEach items="${classAccount.getGroupAccounts()}" var="groupAccount">
                <c:forEach items="${groupAccount.getAccounts()}" var="account">
                    <tr>
                        <td>
                            <fmt:formatNumber type = "number" maxIntegerDigits = "4" value = "${account.bankAccountId}" />
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${account.activeIn}" />
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${account.passiveIn}" />
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${account.debit}" />
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${account.credit}" />
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${account.activeOut}" />
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${account.passiveOut}" />
                        </td>
                    </tr>
                </c:forEach>
                <tr style="font-weight: bold">
                    <td >
                        <fmt:formatNumber type = "number" maxIntegerDigits = "2" value = "${groupAccount.groupAccountId}" />
                    </td>
                    <td>
                        <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${groupAccount.activeIn}" />
                    </td>
                    <td>
                        <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${groupAccount.passiveIn}" />
                    </td>
                    <td>
                        <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${groupAccount.debit}" />
                    </td>
                    <td>
                        <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${groupAccount.credit}" />
                    </td>
                    <td>
                        <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${groupAccount.activeOut}" />
                    </td>
                    <td>
                        <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${groupAccount.passiveOut}" />
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td style="font-weight: bold">
                    ПО КЛАССУ
                </td>
                <td style="font-weight: bold">
                    <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${classAccount.activeIn}" />
                </td>
                <td style="font-weight: bold">
                    <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${classAccount.passiveIn}" />
                </td>
                <td style="font-weight: bold">
                    <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${classAccount.debit}" />
                </td>
                <td style="font-weight: bold">
                    <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${classAccount.credit}" />
                </td>
                <td style="font-weight: bold">
                    <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${classAccount.activeOut}" />
                </td>
                <td style="font-weight: bold">
                    <fmt:formatNumber type = "number" maxIntegerDigits = "15" value = "${classAccount.passiveOut}" />
                </td>
            </tr>
        </c:forEach>
    </table>
</body>