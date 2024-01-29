<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="modelo.Vacina" %>
<%@ page import="java.util.List" %>
<%@ page import="util.VacinaDAO" %>
<%@ page import="modelo.Usuario" %>
<%@ page import="util.UsuarioDAO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="UTF-8">
  <title>Novo Usuário</title>
</head>
<body>
<h1>Dados do Usuário</h1>

<form action="agendacontrol" method="post">

  <input type="hidden" name="acao" value="adicionar"/>
  Usuário:
  <select name="usuarioId">
    <option value="">-</option>
    <%
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("vacinacao");
      EntityManager em = emf.createEntityManager();

      List<Usuario> listaUsuarios = new UsuarioDAO(em).listarUsuarios();

      for (Usuario usuario : listaUsuarios){
    %>
    <option value="<%=usuario.getId()%>"><%=usuario.getNome()%></option>
    <%
      }
    %>
  </select>
  <br>
  Vacina:
  <select name="vacinaId">
    <option value="">-</option>
    <%
      List<Vacina> listaVacinas = new VacinaDAO(em).listarVacinas();

      for (Vacina vacina : listaVacinas){
    %>
    <option value="<%=vacina.getId()%>"><%=vacina.getTitulo()%></option>
    <%
      }
    %>
  </select>
  <br>
  Data:
  <input type="date" value="" name="data" size="60"/>
  <br>
  Horário:
  <input type="time" value="" name="hora" size="60"/>
  <br>
  Observação:
  <input type="text" value="" name="observacao" size="200"/>
  <br>

  <br><br>
  <input type="submit" value="Salvar" name="tipoSalvar"/>

</form>

<br>
<a href="index.jsp">Retornar ao Menu Principal</a>

</body>
</html>