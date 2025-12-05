<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 5.12.25
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<c:if test="${not empty error}">
    <h1>${error}</h1>
</c:if>

<c:if test="${not empty product}">
    <h1>Here is a product!</h1>
    <h1>${product.name}</h1>
    <h1>${product.hashtag}</h1>
    <h1>${product.hashtag2}</h1>

    <c:if test="${not empty product.band2}">
        <h1>${product.band2.name}</h1>
        <h1>${product.band2.history}</h1>
        <h1>${product.band2.albumSales}</h1>
    </c:if>
</c:if>
</body>
</html>
