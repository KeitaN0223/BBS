<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${editUser.name }の編集</title>
</head>
<body>
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

<form action="setting" method="post" enctype="multipart/form-data"><br />

	<label for="account">アカウント名</label>
	<input name="account" value="${editUser.account}" id="account" /><br />

	<label for="name">名前</label>
	<input name="name" value="${editUser.name}" id="name"/><br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<label for="branch_id">支店番号</label>
	<input name="branch_id" value="${editUser.branch_id}" id="branch_id"/><br />

	<label for="department_id">部署または役職番号</label>
	<input name="department_id" value="${editUser.department_id}" id="department_id"/><br />

	<input type="hidden" name = "id" value="${editUser.id }" >
	<input type="submit" value="登録" /> <br />
	<a href="./">戻る</a>
</form>
<div class="copyright">Copyright(c)Keita Nagano</div>
</div>
</body>
</html>

