<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理</title>
</head>
<body>
	<div class="main-contents">
		<div class="header">
			<a href="signup">新規登録</a>
		</div>
		<div class="userAccount">
		<c:forEach items="${account}" var="account">
		<div class="account">
	<form action="admin" method="post">
		<span class="account">アカウント名<c:out value="${account.account }" /></span>
		<span class="name">ユーザー名<c:out value="${account.name }" /></span>
		<input type="hidden" name = "id" value="${account.id }">
		<button id="${account.id }" onclick="alert('${account.name}を停止します')">停止</button>
		<a href="setting?id=${account.id }">編集</a>
	</form>
		</div>
		</c:forEach>
		</div>
		<div class="copyright">Copyright(c)Keita Nagano</div>
	</div>
</body>
</html>