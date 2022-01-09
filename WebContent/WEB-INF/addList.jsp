<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import = "java.util.List" %>
<%@ page import = "com.javaex.vo.GuestbookVo" %>


<%
	List<GuestbookVo> gbList = (List<GuestbookVo>)request.getAttribute("gbList");
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>gb2.addList</title>
</head>

<body>
	<!-- 등록 폼 영역 -->
	<form action = "/guestbook2/gbc" method="get">
		<table border="1" width="500px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			<tr>
			<tr>
				<td colspan="4"><textarea cols="65" rows="5" name = "content" ></textarea></td>
			</tr>
			<tr>
				<td colspan="4">
					<button type="submit" name="action" value="add">글작성</button>
				</td>
			</tr>
		</table>
	</form>
	<!-- 등록 폼 영역 -->
	<br>
	<!-- 리스트 폼 영역 -->
	<%
	for(int i =0; i<gbList.size(); i++){
	%>
		<form action="/guestbook2/gbc" method="get">
			<table border="1" width = "500px">
				<tr>
					<td><%= gbList.get(i).getNo() %></td>
					<td><%= gbList.get(i).getName() %></td>
					<td><%= gbList.get(i).getRegDate() %></td>
					<td><a href = "/guestbook2/gbc?action=deleteForm&no=<%= gbList.get(i).getNo()%>">삭제</a></td>
				</tr>
				<tr>
					<td colspan="4">
						<%= gbList.get(i).getContent() %>
					</td>
				</tr>
			</table>
		</form>
		<br>
	<%
	}
	%>
	<!-- 리스트 폼 영역 -->
</body>

</html>