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
<body>
	<c:if test="${check !=1 }">
		<script>
			alert("아이디 비밀번호 다시"); history.go(-1);
		</script>
	</c:if>
	
	<c:if test="${check == 1 }">
		<c:redirect url="/aopMember/main.do" />
	</c:if>	
</body>
</html>