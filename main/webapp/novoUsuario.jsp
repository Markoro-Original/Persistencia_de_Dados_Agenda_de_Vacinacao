<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
  <meta charset="UTF-8">
  <title>Novo Usuário</title>
</head>
<body>
<h1>Dados do Usuário</h1>

<form action="usuariocontrol" method="post">

  <input type="hidden" name="acao" value="adicionar"/>
  Nome:
  <input type="text" value="" name="nome" size="60"/>
  <br>
  Data de Nascimento:
  <input type="date" value="" name="data_nascimento" size="60"/>
  <br>
  Sexo:
  <select name="sexo">
    <option value="M">Masculino</option>
    <option value="F">Feminino</option>
  </select>
  <br>
  Logradouro:
  <input type="text" value="" name="logradouro" size="60"/>
  <br>
  Número:
  <input type="number" value="" name="numero" size="10">
  <br>
  Setor:
  <input type="text" value="" name="setor" size="40">
  <br>
  Cidade:
  <input type="text" value="" name="cidade" size="40">
  <br>
  UF:
  <select name="uf">
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

  <br><br>
  <input type="submit" value="Salvar" name="tipoSalvar"/>
  <input type="submit" value="Salvar e adicionar Alergia(s)" name="tipoSalvar"/>

</form>

<br>
<a href="index.jsp">Retornar ao Menu Principal</a>

</body>
</html>