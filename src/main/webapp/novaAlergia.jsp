<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Nova Alergia</title>
</head>
<body>
<h1 style="text-align: center;">Dados da Alergia</h1>

<h2 style="text-align: center;">
    <a href="${pageContext.request.contextPath}/alergiacontrol?alergiasMaisComuns=0">Lista de alergias</a>
    &nbsp;&nbsp;&nbsp;
    <a href="index.jsp">Menu Principal</a>
</h2>

<div style="text-align: center; font-size: larger;">
    <form action="alergiacontrol" method="post">

        <div align="center" style="font-size: larger;">
            <table border="1" cellpadding="5">

                <input type="hidden" name="acao" value="adicionar"/>
                <tr>
                    <th align="left">Nome:</th>
                    <td><input type="text" value="" name="nome" size="30"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Salvar"/></td>
                </tr>
            </table>
        </div>

    </form>
</div>

</body>
</html>