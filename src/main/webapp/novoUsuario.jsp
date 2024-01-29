<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="UTF-8">
  <title>Novo Usuário</title>
</head>
<body>
<h1 style="text-align: center;">Dados do Usuário</h1>

<h2 style="text-align: center;">
  <a href="${pageContext.request.contextPath}/usuariocontrol?filtroUF=">Lista de usuários</a>
  &nbsp;&nbsp;&nbsp;
  <a href="index.jsp">Menu Principal</a>
</h2>

<div style="text-align: center; font-size: larger;">
  <form action="usuariocontrol" method="post">

    <div align="center" style="font-size: larger;">
      <table border="1" cellpadding="5">

        <input type="hidden" name="acao" value="adicionar"/>
        <tr>
          <th align="left">Nome:</th>
          <td><input type="text" value="" name="nome" size="40"/></td>
        </tr>
        <tr>
          <th align="left">Data de Nascimento:</th>
          <td><input type="date" value="" name="data_nascimento" size="60"/></td>
        </tr>
        <tr>
          <th align="left">Sexo:</th>
          <td><select name="sexo">
            <option value="M">Masculino</option>
            <option value="F">Feminino</option>
          </select></td>
        </tr>
        <tr>
          <th align="left">Logradouro:</th>
          <td><input type="text" value="" name="logradouro" size="40"/></td>
        </tr>
        <tr>
          <th align="left">Número:</th>
          <td><input type="number" value="" name="numero" size="10"></td>
        </tr>
        <tr>
          <th align="left">Setor:</th>
          <td><input type="text" value="" name="setor" size="40"></td>
        </tr>
        <tr>
          <th align="left">Cidade:</th>
          <td><input type="text" value="" name="cidade" size="40"></td>
        </tr>
        <tr>
          <th align="left">UF:</th>
          <td><select name="uf">
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
          </select></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <input type="submit" value="Salvar" name="tipoSalvar"/>
            &nbsp;&nbsp;&nbsp;
            <input type="submit" value="Salvar e adicionar Alergia(s)" name="tipoSalvar"/>
          </td>
        </tr>
      </table>
    </div>

  </form>

</div>

</body>
</html>