<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Vacinas</title>
</head>
<body>

<h1 style="text-align: center;">Lista de Vacinas</h1>

<h2 style="text-align: center;">
  <a href="novaVacina.jsp">Adicionar vacina</a>
  &nbsp;&nbsp;&nbsp;
  <a href="index.jsp">Menu Principal</a>
</h2>

<div style="text-align: center; font-size: larger;">
  <form action="${pageContext.request.contextPath}/vacinacontrol" method="get">
    <label for="vacinasMaisAgendadas">Ordenar:</label>
    <select name="vacinasMaisAgendadas" id="vacinasMaisAgendadas">
      <option value="0">Por ID</option>
      <option value="1">Mais agendadas</option>
    </select>
    <button type="submit">Ordenar</button>
  </form>
</div>

<div align="center">

  <table border="1" cellpadding="5">
    <tr>
      <th>Id</th>
      <th>Titulo</th>
      <th>Descrição</th>
      <th>Doses</th>
      <th>Periodicidade</th>
      <th>Intervalo</th>
      <th>Número de agendamentos</th>
      <th>Ação:</th>
    </tr>

    <c:forEach var="vacina" items="${listaVacinas}">
      <tr>
        <td>${vacina.id}</td>
        <td>${vacina.titulo}</td>
        <td>${vacina.descricao}</td>
        <td>${vacina.doses}</td>
        <td>${vacina.periodicidade}</td>
        <td>${vacina.intervalo}</td>
        <td>${fn:length(vacina.agendas)}</td>
        <td>
          <br>
          <form action="vacinacontrol" method="post">
            <input type="hidden" name="acao" value="delete"/>
            <input type="hidden" name="deleteId" value="${vacina.id}"/>
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
