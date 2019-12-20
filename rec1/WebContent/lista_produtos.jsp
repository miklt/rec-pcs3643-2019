<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Produtos em Estoque</title>
</head>
<body>
		<div align="center">
		<h1>Produtos </h1>
        <h2>        	
        	<a href="produtos">Listar Produtos</a>
        	
        </h2>
	
    
        <table border="1" cellpadding="5">
            <h2>Lista de Produtos</h2>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Quantidade em Estoquel</th>                
                <th>Acões</th>
            </tr>
            <c:forEach var="produto" items="${listaProdutos}">
                <tr>
                    <td><c:out value="${produto.id}" /></td>
                    <td><c:out value="${produto.nome}" /></td>                    
                    <td><c:out value="${produto.quantidade}" /></td>
                    <td>
                    	<a href="vender?id=<c:out value='${produto.id}' />">Vender</a>                    	
                    	                    	
                    </td>
                </tr>
            </c:forEach>
            
        </table>
    </div>	
</body>
</html>