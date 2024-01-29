<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="UTF-8">
  <title>Nova Vacina</title>
</head>
<body>
<h1>Dados da Vacina</h1>

<form action="vacinacontrol" method="post">

  <input type="hidden" name="acao" value="adicionar"/>
  Título:
  <input type="text" value="" name="titulo" size="60"/>
  <br>
  Descrição:
  <input type="text" value="" name="descricao" size="200"/>
  <br>
  Doses:
  <input type="number" value="" name="doses" size="10"/>
  <br>
  Periodicidade:
  <select name="periodicidade">
    <option value="1">Dias</option>
    <option value="2">Semanas</option>
    <option value="3">Meses</option>
    <option value="4">Anos</option>
  </select>
  <br>
  Intervalo:
  <input type="number" value="" name="intervalo" size="10"/>

  <br><br>
  <input type="submit" value="Salvar"/>

</form>

<br>
<a href="index.jsp">Retornar ao Menu Principal</a>

</body>
</html>