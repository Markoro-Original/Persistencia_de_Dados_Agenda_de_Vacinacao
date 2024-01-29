<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Adicionar Alergia(s) de Usuário</title>
</head>
<body>
<h1 style="text-align: center;">Alergia(s) do Usuário</h1>

<h2 style="text-align: center;">
    <a href="${pageContext.request.contextPath}/usuariocontrol?filtroUF=">Lista de usuários</a>
    &nbsp;&nbsp;&nbsp;
    <a href="index.jsp">Menu Principal</a>
</h2>

<div style="text-align: center; font-size: larger;">
    <form action="usuarioalergiacontrol" method="post">

        <div align="center" style="font-size: larger;">
            <table border="1" cellpadding="5">

                <input type="hidden" name="acao" value="adicionar"/>
                <input type="hidden" name="id" value="${usuario.id}"/>
                <tr>
                    <th align="left">Alergia(s):</th>
                    <td>
                        <select name="alergia">
                            <option value="">-</option>
                            <c:forEach var="alergia" items="${listaAlergias}">
                                <option value="${alergia.id}">${alergia.nome}</option>
                            </c:forEach>
                        </select>
                    <</td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Salvar" name="tipoSalvar"/>
                        &nbsp;&nbsp;&nbsp;
                        <input type="submit" value="Salvar e adicionar outra Alergia" name="tipoSalvar"/>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>

</body>
</html>
