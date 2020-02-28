<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 수정</title>
	<link href="/spring/resources/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
	<h1 align="center"> 글 수정 </h1>
	<form action="/spring/board/modifyPro.do" method="post">
		<%-- 숨겨서 글 속성에 관련된 데이터 전송 --%>
		<input type="hidden" name="num" value="${list.num}" />
		<table>
			<tr>
				<td> 작성자 </td>
				<td align="left"> 
					<input type="text" name="writer" value="${list.writer}" /> 
				</td>
			</tr>
			<tr>
				<td> 제   목 </td>
				<td align="left">
					<input type="text" name="subject" value="${list.subject}"/> 
				</td>
			</tr>
			<tr>
				<td> e-mail </td>
				<td align="left"> 
					<input type="text" name="email" value="${list.email}"/> 
				</td>
			</tr>
			<tr>
				<td> 내   용 </td>
				<td> <textarea rows="20" cols="70" name="content">${list.content}</textarea> </td>
			</tr>
			<tr>
				<td> 비밀번호 </td>
				<td align="left"> <input type="password" name="pw" /> </td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="저장" />
					<input type="reset" value="재작성" />
					<input type="button" value="리스트보기" onclick="window.location='/spring/board/list.do?pageNum=${pageNum}'" />
				</td>
			</tr>
		</table>
	</form>
	


</body>
</html>