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

<h1 style="text-align: center;">Lista de Agendas</h1>

<h2 style="text-align: center;">
    <a href="novaAgenda.jsp">Adicionar agenda</a>
    &nbsp;&nbsp;&nbsp;
    <a href="index.jsp">Menu Principal</a>
</h2>

<div style="text-align: center; font-size: larger;">
    <form action="agendacontrol" method="get">
        <label for="filtroSituacao">Filtrar por Situação:</label>
        <select name="filtroSituacao" id="filtroSituacao">
            <option value="">Todos</option>
            <option value="REALIZADO">Realizado</option>
            <option value="CANCELADO">Cancelado</option>
            <option value="HOJE">Agendados para hoje</option>
        </select>
        <button type="submit">Filtrar</button>
    </form>
</div>

<div align="center">
    <table border="1" cellpadding="5">
        <tr>
            <th>Id</th>
            <th>Usuário</th>
            <th>Vacina</th>
            <th>Data</th>
            <th>Horário</th>
            <th>Situação</th>
            <th>Data de baixa</th>
            <th>Observação</th>
            <th>Dar baixa:</th>
            <th>Ação:</th>

        </tr>

        <c:forEach var="agenda" items="${listaAgendas}">
            <tr>
                <td>${agenda.id}</td>
                <td>
                    <a href="agendacontrol?id=<c:out value='${agenda.usuario.id}'/>">${agenda.usuario.nome}</a>
                </td>
                <td>${agenda.vacina.titulo}</td>
                <td><fmt:formatDate value="${agenda.data}" pattern="dd/MM/yyyy" /></td>
                <td>${agenda.hora}</td>
                <td>${agenda.situacao}</td>
                <td><fmt:formatDate value="${agenda.dataSituacao}" pattern="dd/MM/yyyy" /></td>
                <td>${agenda.observacoes}</td>
                <td>
                    &nbsp;
                    <form action="agendacontrol" method="post">
                        <input type="hidden" name="acao" value="realizar"/>
                        <input type="hidden" name="realizarId" value="${agenda.id}"/>
                        <input type="submit" value="Realizado"/>
                    </form>
                    &nbsp;
                    <form action="agendacontrol" method="post">
                        <input type="hidden" name="acao" value="cancelar"/>
                        <input type="hidden" name="cancelarId" value="${agenda.id}"/>
                        <input type="submit" value="Cancelado"/>
                    </form>
                    &nbsp;
                </td>
                <td>
                    <br>
                    <form action="agendacontrol" method="post">
                        <input type="hidden" name="acao" value="delete"/>
                        <input type="hidden" name="deleteId" value="${agenda.id}"/>
                        <input type="submit" value="Deletar"/>
                    </form>
                    <br>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>
