<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%
 	pageContext.setAttribute("newLine", "\n");
 %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook/add" method="post">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" style="border:none"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password" style="border:none"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
					<c:set var = 'count' value='${fn:length(list) }'/>
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<ul>
							<li>
								<table>
									<tr id = "guestbook" class = "list">
										<td width=20 style="word-break:break-all; text-align:center">${count - status.index }</td>
										<td>${vo.name }</td>
										<td width=100 style="word-break:break-all; text-align:center">${vo.regTime }</td>
										<td width=25 style="word-break:break-all; text-align:center">
											<a href="${pageContext.request.contextPath }/guestbook/delete/${vo.no }">
											<img src='/mysite04/assets/images/recycle.png'/></a></td>
									</tr>
									<tr>
										<td colspan=4>
											${fn:replace(vo.contents, newLine,"<br>") }
										</td>
									</tr>
								</table>
							</li>
						</ul>
					</c:forEach>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>