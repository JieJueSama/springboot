<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width:700px;border:1px solid lightgray;margin:200px;padding:80px">
		系统出现了异常，异常的原因是：
			${exception}
			<br><br>
			出现异常的地址是：
			${url}
	</div>
</body>
</html>