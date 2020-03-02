<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:if test="${check==1}">
	<script>
		alert("수정 완료")
		location.href="/spring/member/main.do"
	</script>
</c:if>
<c:if test="${check==0}"></c:if>
	<script>
		alert("비밀번호 확인 필요")
		history.back()
	</script>
<body>

</body>
</html>