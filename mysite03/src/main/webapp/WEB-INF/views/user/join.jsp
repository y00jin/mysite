<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript">
$(function() {
	$("#btn-checkemail").click(function(){
		var email = $("#email").val();
		if(email == '') {
			return;
		}
		$.ajax({
			url: "${pageContext.request.contextPath }/api/user/checkemail?email=" + email,
			async: true,
			type: 'get',
// 			contentType: 'application/json'
			data: '',
			dataType: 'json',
			success: function(response) {
				if(response.result == 'exist') {
					alert('존재하는 이메일입니다.');
					$("#email").val('')
							   .focus();
					return;
				}
				$('#btn-checkemail').hide();
				$('#img-checkemailbutton').show();
			},
			error: function(XHR, status, e) {
				console.log(status + ":" + e);
			}
		});
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="user">
				<form:form modelAttribute="userVo" 
					id="join-form" name="joinForm" method="post" 
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<form:errors path="name" />
					</p>

					<label class="block-label" for="email">이메일</label>
					<form:input path="email"/>
					<input type="button" id="btn-checkemail" value="중복확인">
					<img id='img-checkemailbutton' style='width: 16px; display: none; margin: 0 auto;'  src='${pageContext.request.contextPath }/assets/images/ok.png' />
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<form:errors path="email" />
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password" />
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<form:errors path="password" />
					</p>
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>