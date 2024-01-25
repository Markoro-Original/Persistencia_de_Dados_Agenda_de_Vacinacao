<%@ page import="negocio.Candidato" %>
<%@ page import="java.util.List" %>
<%@ page import="visao.CandidatoDAO" %>
<%@ page import="controle.ControleCandidato" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Alterar de Candidato</title>
</head>
<body>

<h1>Alterar Candidato</h1>

    <%
        ControleCandidato cd = new ControleCandidato();
    %>

<form action="controlecandidato" method="post">

    <input type="hidden" name="acao" value="alterar"/>
    Codigo:
    <input type="text" value="<%= cd.selectCandidato.getCodigo()%>" name="txtCodigo" size="12" contenteditable="true"/>
    <br>
    Nome:
    <input type="text" value="<%= cd.selectCandidato.getNome() %>" name="txtNome" size="60" contenteditable="true"/>
    <br>
    Sexo:
    <% if(cd.selectCandidato.getSexo() == 'M'){%>
    <select name="selSexo">
        <option value="M" selected>Masculino</option>
        <option value="F">Feminino</option>
    </select>
    <%} else {%>
    <select name="selSexo">
        <option value="M">Masculino</option>
        <option value="F" selected>Feminino</option>
    </select>
    <%}%>
    <br>
    Data de Nascimento:
    <input type="date" value="<%= cd.selectCandidato.getDataNasc() %>" name="dtDataNasc" size="60" contenteditable="true"/>
    <br>
    Cargo Pretendido:
    <input type="text" value="<%= cd.selectCandidato.getCargoPretendido() %>" name="txtCargoPretendido" size="25" contenteditable="true"/>
    <br>
    Texto do Currículo:
    <input type="text" value="<%= cd.selectCandidato.getTextoCurriculo() %>" name="txtTextoCurriculo" size="800"/>
    <br>

    <br><br>
    <input type="submit" value="Salvar"/>

</form>

<br>
<a href="index.html">Retornar ao Menu Principal</a>

</body>
</html>
