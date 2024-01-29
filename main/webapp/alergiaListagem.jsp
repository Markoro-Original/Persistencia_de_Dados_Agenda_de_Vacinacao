<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Alergias</title>
</head>
<body>

<h1>Lista de Alergias</h1>

<form action="${pageContext.request.contextPath}/alergiacontrol" method="get">
    <label for="alergiasMaisComuns">Ordenar:</label>
    <select name="alergiasMaisComuns" id="alergiasMaisComuns">
        <option value="0">Por ID</option>
        <option value="1">Mais comuns</option>
    </select>
    <button type="submit">Ordenar</button>
</form>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Nº de Pessoas com a Alergia</th>
        <th>Ação:</th>
    </tr>

    <c:forEach var="alergia" items="${listaAlergias}">
    <tr>
        <td>${alergia.id}</td>
        <td>${alergia.nome}</td>
        <td>${fn:length(alergia.usuarios)}</td>
        <td>
            <br>
            <form action="alergiacontrol" method="post">
                <input type="hidden" name="acao" value="delete"/>
                <input type="hidden" name="deleteId" value="${alergia.id}"/>
                <input type="submit" value="Deletar"/>
            </form>
            <br>
        </td>

    </tr>
    </c:forEach>

</table>

</body>
</html>