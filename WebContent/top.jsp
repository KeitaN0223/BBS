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
	<a href="logout">ログアウト</a>
</div>
<div class="posts">
	<c:forEach items="${posts}" var="post">
		<div class="post">
			<span class="subject">件名<br />
			<c:out value="${post.subject }"/></span><br /><br />
			<span class="text">本文<br />
			<c:out value="${post.text }"/></span><br /><br />
			<span class="category">カテゴリー<br />
			<c:out value="${post.category }"/></span><br /><br />
			<span class="created_at">投稿時間<br />
			<c:out value="${post.created_at}"/></span><br />
			<span class="name">投稿者<br />
			<c:out value="${post.name }"/></span><br /><br />
				<span class="comment">コメント<br /></span>
				<c:forEach items="${comments }" var="comment">
					<c:if test="${comment.post_id == post.post_id }">
							<c:out value=" ${comment.text }"/><br />
							<c:out value=" ${comment.name }"/><br />
							<c:out value=" ${comment.created_at }"/><br /><br />
					</c:if>
				</c:forEach><br />
		</div>
		<form action="index" method="post">
		<div class="comment">コメントの入力(500文字以内)<br />
			<textarea name="comment" cols="100" rows="5" class="comment-box"></textarea><br />
		</div>
			<input type="hidden" name = "post_id" value="${post.post_id }">
			<input type="submit" value="コメントする"><br /><br />
		</form>

	</c:forEach>
</div>

<div class="copyright">Copyright(c)Keita Nagano</div>
</div>
</body>
</html>