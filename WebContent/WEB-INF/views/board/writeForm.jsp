<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 작성</title>
	<link href="/spring/resources/style.css" rel="stylesheet" type="text/css">
</head>

<body>
	<br />
	<h1 align="center"> 글 작성 </h1>
	<form action="/spring/board/writePro.do" method="post">
		<%-- 숨겨서 글 속성에 관련된 데이터 전송 --%>
		<input type="hidden" name="num" value="${num}" />
		<input type="hidden" name="ref" value="${vo.ref}" />
		<input type="hidden" name="re_step" value="${re_step}" />
		<input type="hidden" name="re_level" value="${re_level}" />
		<input type="hidden" name="readCount" value="${readCount}" />
		<table>
			<tr>
				<td> 작성자 </td>
				<td align="left"> <input type="text" name="writer" /><h4>articlecount = ${num }</h4><h4>ref${ref }</h4><h4>${re_step }</h4><h4>${re_level }</h4><h4>readCount${readCount }</h4> </td>
			</tr>
			<tr>
				<td> 제   목 </td>
				<td align="left">
				
		<!-- 		<c:if test="${num!=null }">   -->					
					<input type="text" name="subject" />
	<!-- 			</c:if> 
					<c:if test="${num==null }">					
					<input type="text" name="subject" value="[답글]"/>
					</c:if>   -->
				</td>
			</tr>
			<tr>
				<td> e-mail </td>
				<td align="left"> <input type="text" name="email" /> </td>
			</tr>
			<tr>
				<td> 내   용 </td>
				<td> <textarea rows="20" cols="70" name="content"></textarea> </td>
			</tr>
			<tr>
				<td> 비밀번호 </td>
				<td align="left"> <input type="password" name="pw" /> </td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="저장" />
					<input type="reset" value="재작성" />
					<input type="button" value="리스트보기" onclick="window.location='/spring/board/list.do'" />
				</td>
			</tr>
		</table>
	</form>
	
	
</body>
</html>