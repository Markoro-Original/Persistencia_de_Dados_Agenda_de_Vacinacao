<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Nova Alergia</title>
</head>
<body>
<h1>Dados da Alergia</h1>

<form action="alergiacontrol" method="post">

    <input type="hidden" name="acao" value="adicionar"/>
    Nome:
    <input type="text" value="" name="nome" size="60"/>

    <br><br>
    <input type="submit" value="Salvar"/>

</form>

<br>
<a href="index.jsp">Retornar ao Menu Principal</a>

</body>
</html>