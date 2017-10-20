<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Spring 3 MVC Multipe Row Submit - viralpatel.net</title>
</head>
<body>
<h2>Show Contacts</h2>
<table width="50%">
    <tr>
        <th>Name</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Phone</th>
    </tr>
    <c:forEach items="${productOrder.products}" var="productOrder">
        <tr>
            <td>${products.clientId}</td>
            <td>${products.id}</td>
            <td>${products.product}</td>
            <td>${products.firmaId}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<input type="button" value="Back" onclick="javascript:history.back()"/>
</body>
</html>