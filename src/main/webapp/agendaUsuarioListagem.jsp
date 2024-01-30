<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Agendas</title>
</head>
<body>

<h1 style="text-align: center;">Agendamentos de ${nome}</h1>

<h2 style="text-align: center;">
    <a href="${pageContext.request.contextPath}/usuariocontrol?filtroUF=">Lista de usuários</a>
    &nbsp;&nbsp;&nbsp;
    <a href="index.jsp">Menu Principal</a>
</h2>

<div align="center">
    <table border="1" cellpadding="5">
        <tr>
            <th>Vacina</th>
            <th>Data</th>
            <th>Situação</th>
            <th>Dose</th>

        </tr>

        <c:forEach var="agenda" items="${listaAgendas}">
            <tr>
                <td>${agenda.vacina.titulo}</td>
                <td><fmt:formatDate value="${agenda.data}" pattern="dd/MM/yyyy" /></td>
                <td>${agenda.situacao}</td>
                <td>${agenda.dose}</td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>
