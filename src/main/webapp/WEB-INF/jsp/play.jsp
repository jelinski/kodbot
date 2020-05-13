<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
div.panel>img {
	margin: 0px 15px;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	border: 0px solid black;
}

img.level {
	border: 2px solid green;
}

img.level:hover {
	border: 2px solid greenyellow;
}
</style>

<spring:message code="web.content.play.level.alt" var="level_text" />

<div class="row" style="margin: 30px 0px">
	<c:forEach items="${mapGalleryList}" var="map">
		<div class="col-xs-6 col-md-4">
			<div style="position: relative" class="game-thumbnail-div">

				<div style="position: absolute">
					<c:url value="/resources/images/content/play/${map.imageUrl}.png" var="imageUrl" />
					<c:url value="/game/${map.gameUrl}" var="gameUrl" />
					<a href="${gameUrl}">
						<img alt="Game preview thumbnail" src="${imageUrl}" />
					</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>


<!-- </div> -->