<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Adicionar Alergia(s) de Usuário</title>
</head>
<body>
<h1>Alergia(s) do Usuário</h1>

<form action="usuarioalergiacontrol" method="post">
    <input type="hidden" name="acao" value="adicionar"/>
    <input type="hidden" name="id" value="${usuario.id}"/>
    Alergia(s):
    <select name="alergia">
        <option value="">-</option>
        <c:forEach var="alergia" items="${listaAlergias}">
            <option value="${alergia.id}">${alergia.nome}</option>
        </c:forEach>
    </select>

    <br><br>
    <input type="submit" value="Salvar" name="tipoSalvar"/>
    <input type="submit" value="Salvar e adicionar outra Alergia" name="tipoSalvar"/>

</form>

</body>
</html>
