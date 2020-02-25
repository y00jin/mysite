<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
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
<!-- 			<div id="board" class = "board-form"> -->
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board/search" method="post">
					<select name="kind">
		   				 <option value="selected">-- 선택 --</option>
		  				  <option value="title">제목</option>
		  				  <option value="name">글쓴이</option>
					</select>
					<input type="text" id="kwd" name="kwd" value=""> 
					<input type="submit" value="찾기">
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
					
					<c:choose>
						<c:when test="${ListOrSearch == 0}">
							<c:set var = 'list' value = "${boardList }" />
						</c:when>
						<c:when test="${ListOrSearch == 1}">
							<c:set var = 'list' value = "${searchList }" />
						</c:when>
					</c:choose>
					
					<c:set var = 'count' value='${fn:length(list) }'/>
					<c:choose>
						<c:when test="${count == 0 && ListOrSearch == 1}">
						<tr><td colspan="6">검색된 게시물이 존재하지 않습니다.</td></tr>
						</c:when>
						<c:otherwise>
							<c:forEach items='${list }'  var='vo' varStatus='status'>
								<tr>
									<td>${vo.no}</td>
										<c:choose>
											<c:when test="${vo.orderNo > 1}">
												<td style="text-align:left; padding-left:${20*vo.depth}px">
													<img src='/mysite02/assets/images/reply.png'/>
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
												<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a>
													</td>
													<td>${vo.userName }</td>
													<td>${vo.hit }</td>
													<td>${vo.regDate }</td>
													<td>
														<c:if test="${authUser.no == vo.userNo }">
															<a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no}&ono=${vo.orderNo}&gno=${vo.groupNo}&dep=${vo.depth}" class="del">삭제</a>
														</c:if>
													</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>							
						</c:otherwise>
					</c:choose>
				</table>

				<c:choose>
						<c:when test="${ListOrSearch == 0}">
							<c:set var = 'pageVo' value = "${listPageVo }" />
						</c:when>
						<c:when test="${ListOrSearch == 1}">
							<c:set var = 'pageVo' value = "${searchPageVo }" />
						</c:when>
				</c:choose>


				<div class="pager">
					<ul>
						<c:if test="${pageVo.thisPage != 1}">
							<li><a href="${pageContext.request.contextPath }/board?a=list&no=${pageVo.thisPage-1 }&los=${ListOrSearch}">◀</a></li>
						</c:if>
						
						<c:forEach var="i" begin = "${pageVo.startPage }" end = "${pageVo.endPage}" step="1">
						<c:if test="${i == pageVo.thisPage }">
							<li class="selected"><a href="${pageContext.request.contextPath }/board?a=list&no=${i }&los=${ListOrSearch}">${i }</a></li>
						</c:if>
						<c:if test="${i != pageVo.thisPage }">
							<li><a href="${pageContext.request.contextPath }/board?a=list&no=${i }&los=${ListOrSearch}">${i }</a></li>
						</c:if>
						</c:forEach>
						
						<c:if test="${pageVo.thisPage != pageVo.endPage }">
							<li><a href="${pageContext.request.contextPath }/board?a=list&no=${pageVo.thisPage+1 }&los=${ListOrSearch}">▶</a></li>
						</c:if>
					</ul>
				</div>


				<c:choose>
				
					<c:when test="${authUser.no != null }">
						<div class="bottom">
							<c:if test="${ListOrSearch == 1}">
								<a href="${pageContext.request.contextPath }/board?a=list&no=1" id="new-book">글목록</a>
							</c:if>
							<a href="${pageContext.request.contextPath }/board?a=writeform&no=${authUser.no}" id="new-book">글쓰기</a>
						</div>					
					</c:when>
					
					<c:otherwise>
						<div class="bottom">
							<c:if test="${ListOrSearch == 1}">
								<a href="${pageContext.request.contextPath }/board?a=list&no=1"  id="new-book">글목록</a>
							</c:if>
						</div>					
					</c:otherwise>
					
				</c:choose>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
		<c:param name="menu" value="board" /></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>