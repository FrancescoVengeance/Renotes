<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding ="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">

<head>
    <title>Verification code</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
  	<meta http-equiv="x-ua-compatible" content="ie=edge">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  	<link href="css/bootstrap.min.css" rel="stylesheet">
  	<link href="css/mdb.min.css" rel="stylesheet">
  	<link href="css/style.min.css" rel="stylesheet">
  	<link href="css/twofactor-custom.css" rel="stylesheet">
</head>

<body>
    <form action="TwoFactorAutenticationServlet" method="POST">      
        <div class="md-form">
          <i class="fas fa-key prefix grey-text"></i>
          <input type="number" min="0" class="form-control" name="verification-code" required>
          <p class="mx-5">Inserisci codice di conferma</p>
          <button class="btn btn-success button-centred" type="submit">Invia</button>
        </div>
    </form>    
</body>

</html>