<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align='center' border='1' cellspacing='0'>
		<tr>
			<td>id</td>
			<td>name</td>
			<td>编辑</td>
			<td>删除</td>
		</tr>
		<c:forEach	items="${cs}" var="c" varStatus="st">
			<tr>
				<td>${c.id}</td>
				<td>${c.name}</td>
				<td><a href="editCategory?id=${c.id}">编辑</a></td>
				<td><a href="deleteCategory?id=${c.id}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<div><a></a></div>
</body>
</html>