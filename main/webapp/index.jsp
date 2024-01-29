<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agendamento de Vacinas</title>
</head>
<body>

<h1>Menu Principal</h1>


<a href="novaVacina.jsp">Adicionar vacina</a>
<br>
<a href="${pageContext.request.contextPath}/vacinacontrol?vacinasMaisAgendadas=0">Lista de vacinas</a>
<br>
<a href="novaAlergia.jsp">Adicionar alergia</a>
<br>
<a href="${pageContext.request.contextPath}/alergiacontrol?alergiasMaisComuns=0">Lista de alergias</a>
<br>
<a href="novoUsuario.jsp">Adicionar usuário</a>
<br>
<a href="${pageContext.request.contextPath}/usuariocontrol?filtroUF=">Lista de usuários</a>
<br>
<a href="novaAgenda.jsp">Adicionar agenda</a>
<br>
<a href="${pageContext.request.contextPath}/agendacontrol?filtroSituacao=">Listar agendamentos</a>
</body>
</html>
