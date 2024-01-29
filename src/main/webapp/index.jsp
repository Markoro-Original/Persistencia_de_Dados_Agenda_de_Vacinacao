<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Agendamento de Vacinas</title>
</head>
<body>

<h1 style="text-align: center;">Menu Principal</h1>

<h2 style="text-align: center;">
    &nbsp;&nbsp;
    <a href="novaVacina.jsp">Adicionar vacina</a>
    &nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/vacinacontrol?vacinasMaisAgendadas=0">Lista de vacinas</a>
</h2>

<h2 style="text-align: center;">
    &nbsp;&nbsp;
    <a href="novaAlergia.jsp">Adicionar alergia</a>
    &nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/alergiacontrol?alergiasMaisComuns=0">Lista de alergias</a>
</h2>

<h2 style="text-align: center;">
    &nbsp;&nbsp;
    <a href="novoUsuario.jsp">Adicionar usuário</a>
    &nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/usuariocontrol?filtroUF=">Lista de usuários</a>
</h2>

<h2 style="text-align: center;">
    <a href="novaAgenda.jsp">Adicionar agenda</a>
    &nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/agendacontrol?filtroSituacao=">Listar agendas</a>
</h2>

</body>
</html>
