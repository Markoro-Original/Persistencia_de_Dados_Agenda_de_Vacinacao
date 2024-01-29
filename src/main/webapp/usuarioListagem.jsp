<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Usuários</title>
</head>
<body>

<h1 style="text-align: center;">Lista de Usuários</h1>

<h2 style="text-align: center;">
  <a href="novoUsuario.jsp">Adicionar usuário</a>
  &nbsp;&nbsp;&nbsp;
  <a href="index.jsp">Menu Principal</a>
</h2>

<div style="text-align: center; font-size: larger;">
  <form action="${pageContext.request.contextPath}/usuariocontrol" method="get">
    <label for="filtroUF">Filtro de UF:</label>
    <select name="filtroUF" id="filtroUF">
      <option value="">Todas</option>
      <option value="AC">AC</option>
      <option value="AL">AL</option>
      <option value="AP">AP</option>
      <option value="AM">AM</option>
      <option value="BA">BA</option>
      <option value="CE">CE</option>
      <option value="DF">DF</option>
      <option value="ES">ES</option>
      <option value="GO">GO</option>
      <option value="MA">MA</option>
      <option value="MT">MT</option>
      <option value="MS">MS</option>
      <option value="MG">MG</option>
      <option value="PA">PA</option>
      <option value="PB">PB</option>
      <option value="PR">PR</option>
      <option value="PE">PE</option>
      <option value="PI">PI</option>
      <option value="RJ">RJ</option>
      <option value="RN">RN</option>
      <option value="RS">RS</option>
      <option value="RO">RO</option>
      <option value="RR">RR</option>
      <option value="SC">SC</option>
      <option value="SP">SP</option>
      <option value="SE">SE</option>
      <option value="TO">TO</option>
    </select>
    <button type="submit">Filtrar</button>
    &nbsp;&nbsp;&nbsp;
    <label>Número total de Usuários:</label>
    <a>${fn:length(listaUsuarios)}</a>
  </form>
</div>

<div align="center">
  <table border="1" cellpadding="5">
    <tr>
      <th>Id</th>
      <th>Nome</th>
      <th>Data de Nascimento</th>
      <th>Sexo</th>
      <th>Alergia(s)</th>
      <th>Logradouro</th>
      <th>Número</th>
      <th>Setor</th>
      <th>Cidade</th>
      <th>UF</th>
      <th>Ação:</th>
    </tr>

    <c:forEach var="usuario" items="${listaUsuarios}">
      <tr>
        <td>${usuario.id}</td>
        <td><a href="agendacontrol?id=<c:out value='${usuario.id}'/>">${usuario.nome}</a></td>
        <td><fmt:formatDate value="${usuario.data_nascimento}" pattern="dd/MM/yyyy" /></td>
        <td>${usuario.sexo}</td>
        <td>
          <c:forEach var="alergia" items="${usuario.alergias}">
            ${alergia.nome}
            <br/>
          </c:forEach>
          <br>
          <a href="usuarioalergiacontrol?id=<c:out value='${usuario.id}'/>">Adicionar Alergia</a>
        </td>
        <td>${usuario.logradouro}</td>
        <td>${usuario.numero}</td>
        <td>${usuario.setor}</td>
        <td>${usuario.cidade}</td>
        <td>${usuario.uf}</td>
        <td>
          <br>
          <form action="usuariocontrol" method="post">
            <input type="hidden" name="acao" value="delete"/>
            <input type="hidden" name="deleteId" value="${usuario.id}"/>
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