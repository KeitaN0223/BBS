<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
</head>
<body>
<div class="main-contents">
<div class="header">
	<a href="post">新規投稿画面</a>
	<a href="admin">ユーザー管理画面</a>
</div>
<div class="posts">
	<c:forEach items="{posts}" var="post">
		<div class="post">
			<div class="subject">
				<span class="subject"><c:out value="${posts.subject }"/></span>
				<span class="post_text"><c:out value="${posts.text }"/></span>
				<span class="post_created_at"><c:out value="${posts.created_at}"/></span>
				<span class="post_name"><c:out value="${posts.name }"/></span>
				<span class="comment"><c:out value="${posts.comment }"/></span>
			</div>
		</div>
	</c:forEach>
</div>

<div class="copyright">Copyright(c)Keita Nagano</div>
</div>
</body>
</html>