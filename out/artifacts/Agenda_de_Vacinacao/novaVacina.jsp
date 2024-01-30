<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="UTF-8">
  <title>Nova Vacina</title>
</head>
<body>

<h1 style="text-align: center;">Dados da Vacina</h1>

<h2 style="text-align: center;">
  <a href="${pageContext.request.contextPath}/vacinacontrol?vacinasMaisAgendadas=0">Lista de vacinas</a>
  &nbsp;&nbsp;&nbsp;
  <a href="index.jsp">Menu Principal</a>
</h2>

<div style="text-align: center; font-size: larger;">
  <form action="vacinacontrol" method="post">

    <div align="center" style="font-size: larger;">
      <table border="1" cellpadding="5">
        <input type="hidden" name="acao" value="adicionar"/>
        <tr>
          <th align="left">Título:</th>
          <td><input type="text" value="" name="titulo" size="20"/></td>
        </tr>
        <tr>
          <th align="left">Descrição:</th>
          <td><input type="text" value="" name="descricao" size="30"/></td>
        </tr>
        <tr>
          <th align="left">Doses:</th>
          <td><input type="number" value="" name="doses" size="3"/></td>
        </tr>
        <tr>
          <th align="left">Periodicidade:</th>
          <td><select name="periodicidade">
          <option value="1">Dias</option>
          <option value="2">Semanas</option>
          <option value="3">Meses</option>
          <option value="4">Anos</option>
        </select></td>
        </tr>
        <tr>
          <th align="left">Intervalo:</th>
          <td><input type="number" value="" name="intervalo" size="3"/></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <input type="submit" value="Salvar"/>
          </td>
        </tr>
      </table>
    </div>

  </form>
</div>

</body>
</html>