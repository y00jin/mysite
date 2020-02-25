<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="header">
	<h1><a href="${pageContext.request.contextPath }">MySite</a></h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a><li>
				<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a><li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/user/update">회원정보수정</a><li>
				<li><a href="${pageContext.request.contextPath }/user/logout" onclick="return logoutChk();">로그아웃</a><li>
				<script type="text/javascript">
					function logoutChk() {
						return confirm("로그아웃 하시겠습니까?");
					}
				</script>
				<li>${authUser.name }님</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>