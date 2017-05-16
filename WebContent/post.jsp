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
<form action="post" method="post">

<div class="subject">
	件名<br />
	<textarea name="subject" rows="1" cols="100" class="subject-box"></textarea>
	<br />
</div>

<div class="form-area">
	投稿内容<br />
	<textarea name="post" cols="100" rows="10" class="tweet-box"></textarea>
	<br />
</div>

<div class="category">
	カテゴリー<br />
	<textarea name="category" rows="1" cols="20" class="category-box"></textarea>
</div>
	<input type="submit" value="投稿">(1000文字以内)
</form>
</body>
</html>