<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
    function oneCheckbox(a){
        var obj = document.getElementsByName("chk");
        for(var i=0; i<obj.length; i++){
            if(obj[i] != a){
                obj[i].checked = false;
            }
        }
    }
</script>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="get">
					<p>
					<input type="checkbox" id="check" name = "chk" value="title" onclick="oneCheckbox(this)">제목
					<input type="checkbox" id="check" name = "chk" value="name" onclick="oneCheckbox(this)">글쓴이
					<input type="text" id="kwd" name="kwd" value="${keyword }"> 
					<input type="submit" value="찾기">
					</p>
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:set var = 'count' value='${fn:length(map.list) }'/>
							<c:forEach items='${list }'  var='vo' varStatus='status'>
								<tr>
									<td>${vo.no}</td>
										<c:choose>
											<c:when test="${vo.orderNo > 1}">
												<td style="text-align:left; padding-left:${20*vo.depth}px">
													<img src='/mysite04/assets/images/reply.png'/>
											</c:when>
											<c:otherwise>
												<td style="text-align:left; padding-left:${0*0}px">
											</c:otherwise>
										</c:choose>
										
										<c:choose>
											<c:when test="${empty vo.contents}">
												<a>(원글이 삭제된 글) ${vo.title }</a></td>
												<td></td><td></td><td></td><td></td>
											</c:when>
											<c:otherwise>
												<a href="${pageContext.request.contextPath }/board/view/${vo.no}">${vo.title }</a>
													</td>
													<td>${vo.userName }</td>
													<td>${vo.hit }</td>
													<td>${vo.regDate }</td>
													<td>
														<c:if test="${authUser.no == vo.userNo }">
															<a href="${pageContext.request.contextPath }/board/delete/${vo.no}" class = "del">삭제</a>
														</c:if>
													</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>	
				</table>
				<div class="pager">
					<ul>
						<c:if test="${currentPage != 1}" >
							<li><a href="${pageContext.request.contextPath }/board?p=${currentPage - 1 }&chk=${chk }&kwd=${keyword }">◀</a></li>
						</c:if>
						<c:forEach begin="${beginPage }" end="${beginPage + listSize - 1}" var="page">
							<c:choose>
								<c:when test="${endPage < page }">
									<li>${page }</li>
								</c:when> 
								<c:when test="${currentPage == page }">
									<li class="selected">${page }</li>
								</c:when>
								<c:otherwise> 
									<li><a href="${pageContext.request.contextPath }/board?p=${page }&chk=${chk }&kwd=${keyword }">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${currentPage < endPage }" >
							<li><a href="${pageContext.request.contextPath }/board?p=${currentPage + 1 }&chk=${chk }&kwd=${keyword }">▶</a></li>
						</c:if>	
					</ul>
				</div>

				<div class="bottom">
					<c:choose>
						<c:when test="${authUser.no != null }">
							<a href="${pageContext.request.contextPath }/board" id="new-book">글목록</a><a></a>
							<a href="${pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/board" id="new-book">글목록</a>
						</c:otherwise>
					</c:choose>
				</div>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
		<c:param name="menu" value="board" /></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>