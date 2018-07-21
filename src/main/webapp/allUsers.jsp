<%@page import="pojo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>All Users</h1>
	<table>
		<body>
			<c:forEach items="${users}" var="u">
				<tr>
					<td>${u.id }</td>
					<td>${u.uname }</td>
					<td>${ u.sex}</td>
					<td>${u.age}</td>
				</tr>
			</c:forEach></body>
	</table>
</body>
</html>