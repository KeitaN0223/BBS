<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<a href="admin">戻る</a>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="signup" method="post"><br />

	<label for="account">アカウント名</label>
	<input name="account" value="${user.account }" id="account"/><br />

	<label for="name">名前</label>
	<input name="name" value="${user.name }" id="name"/><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/><br />

	<label for="confirm_password">パスワード確認</label>
	<input name="confirm_password" type="password" id="confirm_password"/><br />

	<label for="branch_id">支店名</label>
	<select name = "branch_id" size = "1">
		<c:forEach items="${branches}" var="branch">
			<option value = "${branch.id}">
				<c:out value ="${branch.name}"/>
			</option>
		</c:forEach>
	</select><br />

	<label for="department_id">部署または役職番号</label>
	<select name ="department_id" size ="1">
		<c:forEach items="${departments}" var="department">
			<option value = "${department.id}">
				<c:out value ="${department.name}"/>
			</option>
		</c:forEach>
	</select><br />


	<input type="submit" value="登録" /><br />
</form>


</div>

<div class="copyright">Copyright(c)Keita Nagano</div>

</body>
</html>