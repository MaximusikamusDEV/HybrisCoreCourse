<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 7.12.25
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Contact Request</title>
</head>
<body>
<h2>Find Contact Request</h2>
<form method="get" action="getContact">
    <label for="sender">Sender:</label>
    <input type="text" id="sender" name="sender"/>
    <input type="submit" value="Search"/>
</form>

<hr/>

<h2>Create New Contact Request</h2>
<form method="post" action="addContact">
    <label for="newSender">Sender:</label>
    <input type="text" id="newSender" name="newSender"/>
    <br/><br/>
    <label for="newMessage">Message:</label>
    <textarea id="newMessage" name="newMessage" rows="5" cols="40"></textarea>
    <br/><br/>
    <input type="submit" value="Send"/>
</form>

<hr/>

<c:if test="${not empty error}">
    <p style="color: red;">Error: ${error}</p>
</c:if>

<c:if test="${not empty contactRequest}">
    <h2>Contact Request Details</h2>
    <p><strong>Sender:</strong> ${contactRequest.sender}</p>
    <p><strong>Message:</strong> ${contactRequest.message}</p>
</c:if>
</body>
</html>

