<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 내용</title>
	<link href="/spring/resources/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
	<h1 align="center"> content </h1>
	
	<table>
		<tr>
			<td colspan="2"><b>${list.subject}</b></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="20" cols="70" readonly>${list.content}</textarea> </td>
		</tr>
		<tr>
			<td>posted by <a href="mailto:${list.email}">${list.writer}</a> 
			at ${list.reg} </td>
			
			<td>${list.readCount} viewed</td>
		</tr>
		<tr>
			<td colspan="2">
				<h4>pageNum = ${pageNum } , num = ${list.num}</h4>
				<button onclick="window.location='/spring/board/modifyForm.do?num=${list.num}&pageNum=${pageNum}'">수   정</button>
				<button onclick="window.location='/spring/board/deleteForm.do?num=${list.num}&pageNum=${pageNum}'">삭   제</button>
				<%-- 답글버튼처리 : num,ref,re_step,re_level DB에서 받은정보 보내주면서 이동 --%>
				<button onclick="window.location='/spring/board/writeForm.do?num=${list.num}&ref=${list.ref}&re_step=${list.re_step}&re_level=${list.re_level}'">답   글</button>
				<button onclick="window.location='/spring/board/list.do?pageNum=${pageNum}'" >리스트</button>
			</td>
		</tr>
	</table>
	
</body>
</html>