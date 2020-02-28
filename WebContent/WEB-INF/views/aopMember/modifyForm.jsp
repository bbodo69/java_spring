<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/spring/resources/style.css" rel="stylesheet" type="text/css" >
</head>

<c:if test="${sessionScope.memId == null}">
 	<c:redirect url="/aopMember/main.do" />
</c:if>

<c:if test="${sessionScope.memId != null}">

<body>	
	<br />
	<h2 align="center" > 정보수정 </h2>
	<form action="/spring/aopMember/modifyPro.do" method="post">
		<table>
			<tr>
			<td>아이디 * ${userId } </td>
			<td><input type="text" name="id" value="${userId }"/></td>
		</tr>		
		<tr>
			<td>비밀번호 * </td>
			<td><input type="password" name="pw" value="${userPw }" /></td>
		</tr>
		<tr>
			<td>비밀번호 확인 * </td>
			<td><input type="password" name="pwCh" /></td>
		</tr>
		<tr>
			<td>이름 * </td>
			<td><input type="text" name="name" value="${userName }"/></td>
		</tr>
		<tr>
			<td>나이 * </td>
			<td><input type="text" name="age" value="${userAge }"/></td>
		</tr>
		<tr>
			<td>Email </td>
			<td><input type="text" name="email" value="${userEmail }"/></td>
		</tr>		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="정보수정" />
				<input type="reset" value="재입력" /> 
				<input type="button" value="취소" onclick="window.location.href='/spring/aopMember/main.do'" /> 
			</td>
		</tr>
		</table>
	</form>
</body>
</c:if>
</html>
