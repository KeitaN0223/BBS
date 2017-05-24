<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
<a href="./">戻る</a>
<c:forEach items="${errorMessages }" var="message">
				<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:forEach>
<form action="post" method="post">

<div class="subject">
	件名(50文字以下で入力してください)<br />
	<textarea name="subject" rows="1" cols="100" class="subject-box"><c:out value ="${post.subject}"/></textarea>
	<br />
</div>

<div class="form-area">
	本文(1000文字以下で入力してください)<br />
	<textarea name="post" cols="100" rows="10" class="tweet-box" ><c:out value ="${post.text}"/></textarea>
	<br />
</div>

<div class="category">
	カテゴリー(10文字以下で入力してください)<br />
	<textarea name="category" rows="1" cols="20" class="category-box"><c:out value ="${post.category}"/></textarea>
</div>
	<input type="submit" value="投稿">
</form>
</body>
</html>