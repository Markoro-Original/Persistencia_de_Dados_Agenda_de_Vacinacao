<%@ page import="negocio.Candidato" %>
<%@ page import="java.util.List" %>
<%@ page import="biblioteca.CandidatoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Alterar Candidato</title>
</head>
<body>

<h1>Alterar Candidato</h1>

<form action="controlecandidato" method="post">

  <input type="hidden" name="acao" value="selecionar"/>
  Informe o codigo do candidato a ser alterado:
  <input type="text" value="" name="txtCodigo" size="12"/>

  <br>
  <input type="submit" value="Confirmar"/>
  <br><br>

</form>

<table border="1">
  <tr>
    <th>Código</th>
    <th>Nome</th>
    <th>Sexo</th>
    <th>Data de Nascimento</th>
    <th>Cargo Pretendido</th>
    <th>Texto do Currículo</th>
  </tr>

  <%
    List<Candidato> listaCandidatos = new CandidatoDAO().getCandidatos();

    for (Candidato candidato : listaCandidatos) {
  %>
  <tr>
    <td><%= candidato.getCodigo() %></td>
    <td><%= candidato.getNome() %></td>
    <td><%= candidato.getSexo() %></td>
    <td><%= candidato.getDataNasc() %></td>
    <td><%= candidato.getCargoPretendido() %></td>
    <td><%= candidato.getTextoCurriculo() %></td>
  </tr>
  <%
    }
  %>

</table>

<br>
<a href="index.html">Retornar ao Menu Principal</a>

</body>
</html>
