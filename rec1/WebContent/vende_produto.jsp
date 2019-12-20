<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Vender Produto</title>
</head>
<body>
	<div align="center">
		<h1>Vender Produto</h1>

		<c:if test="${produto != null}">
			<form action="vender" method="post">
		</c:if>


		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${produto != null}">
            			Vender
            		</c:if>
 
				</h2>
			</caption>
			<c:if test="${produto != null}">
				<input type="hidden" name="id_produto"	value="<c:out value='${produto.id}' />" />
				<input type="hidden" name="quantidade_no_estoque"
					value="<c:out value='${produto.quantidade}' />" />
			</c:if>
			<tr>
				<th>Nome do Produto:</th>
				<td><c:out value='${produto.nome}' /></td>
			</tr>
			<tr>
				<th>Quantidade em Estoque</th>
				<td> <c:out value='${produto.quantidade}'/>
				</td>
			</tr>
			<tr>
				<th>Quantidade a ser vendida</th>
				<td><input type="text" name="quantidade_da_venda" size="45"
					value="" />
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Vender" /></td>
			</tr>
			
		</table>

		</form>


	</div>
</body>
</html>
