<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理</title>
<script type="text/javascript">
<!--

function disp(str){
	if(confirm(str+"しますか？")){
		return true;
	}else{
		return false;
	}
}

// -->
</script>
</head>
<body>
	<div class="main-contents">
		<div class="header">
			<a href="signup">新規登録</a>
			<a href="index">ホーム画面</a>
		</div>
		<div class="userAccount">
		<c:forEach items="${account}" var="account">
		<div class="account">
	<form action="admin" method="post">
		<span class="account">アカウント名<c:out value="${account.account }" /></span>
		<span class="name">ユーザー名<c:out value="${account.name }" /></span>
		<input type="hidden" name = "id" value="${account.id }">

		${account.is_stopped }
		<c:choose>
			<c:when test ="${account.is_stopped == 0}">
				<input type="hidden" name = "is_stopped" value="1">
				<input type="submit" value="停止" onClick="return disp('${account.name}を停止');">
			</c:when>
			<c:otherwise>
				<input type="hidden" name = "is_stopped" value="0">
				<input type="submit" value="復活" onClick="return disp('${account.name}を復活');">
			</c:otherwise>
		</c:choose>
		<a href="setting?id=${account.id }">編集</a>
	</form>
		</div>
		</c:forEach>
		</div>
		<div class="copyright">Copyright(c)Keita Nagano</div>
	</div>
</body>
</html>