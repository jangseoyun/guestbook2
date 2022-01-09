<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	int no = Integer.parseInt(request.getParameter("no"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gb2.deleteForm</title>
</head>

<body>

	<form action="/guestbook2/gbc" method="get">
		비밀번호 <input type="text" name="password" value="">
		<button type="submit" name="action" value="delete">확인</button> <br>	
		<input type="text" name="no" value="<%= no %>">
	</form>
	
	<a href="/guestbook2/gbc?action=addList">메인으로 돌아가기</a> <br>
	
</body>

</html>