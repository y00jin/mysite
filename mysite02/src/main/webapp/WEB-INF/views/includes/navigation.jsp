<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath }">전유진</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook?a=list">방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
	</ul>
</div>