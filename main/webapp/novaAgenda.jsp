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
  <title>Novo Agendamento</title>
</head>
<body>

<h1 style="text-align: center;">Dados do Agendamento</h1>

<h2 style="text-align: center;">
  <a href="${pageContext.request.contextPath}/agendacontrol?filtroSituacao=">Listar agendamentos</a>
  &nbsp;&nbsp;&nbsp;
  <a href="index.jsp">Menu Principal</a>
</h2>

<div style="text-align: center; font-size: larger;">
  <form action="agendacontrol" method="post">

    <div align="center" style="font-size: larger;">
      <table border="1" cellpadding="5">

        <input type="hidden" name="acao" value="adicionar"/>
        <tr>
          <th align="left">Usuário:</th>
          <td><select name="usuarioId">
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
        </select></td>
        </tr>
        <tr>
          <th align="left">Vacina:</th>
          <td><select name="vacinaId">
          <option value="">-</option>
          <%
            List<Vacina> listaVacinas = new VacinaDAO(em).listarVacinas();

            for (Vacina vacina : listaVacinas){
          %>
          <option value="<%=vacina.getId()%>"><%=vacina.getTitulo()%></option>
          <%
            }
          %>
        </select></td>
        </tr>
        <tr>
          <th align="left">Data:</th>
          <td><input type="date" value="" name="data" size="60"/></td>
        </tr>
        <tr>
          <th align="left">Horário:</th>
          <td><input type="time" value="" name="hora" size="60"/></td>
        </tr>
        <tr>
          <th align="left">Observação:</th>
          <td><input type="text" value="" name="observacao" size="60"/></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <input type="submit" value="Salvar" name="tipoSalvar"/>
          </td>
        </tr>
      </table>
    </div>
  </form>
</div>

</body>
</html>