<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding ="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">

<head>
    <title>Verification code</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">    
</head>

<body>
    <form action="TwoFactorAutenticationServlet" method="POST"> 
        <input type="" name="verification-code">
        <button type="submit">Invia</button>
    </form>    
</body>

</html>