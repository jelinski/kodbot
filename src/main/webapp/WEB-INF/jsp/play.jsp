<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<!-- <div class="panel" style="background-color: #252525; text-align: center; margin-top: 30px; margin-bottom: 30px; padding: 30px 0px"> -->

<spring:message code="web.content.play.level.alt" var="level_text" />

<div class="row" style="margin: 30px 0px">
	<c:forEach items="${mapGalleryList}" var="map">
		<div class="col-xs-6 col-md-4">
			<div style="position: relative" class="game-thumbnail-div">

				<div style="position: absolute">
					<c:url value="/resources/images/content/play/${map.imageUrl}.png" var="imageUrl" />
					<c:url value="/game/${map.gameUrl}" var="gameUrl" />
					<a href="${gameUrl}">
						<img src="${imageUrl}" />
					</a>
				</div>
				<div style="position: relative; background-color: rgba(0, 0, 0, 0.6); height:75px;">
					<div style="width: 32%; padding-left:5px; display: inline-block; vertical-align: middle;">
						<div title='<spring:message code="web.content.play.command_counter" />'>
							<c:choose>
								<c:when test="${empty map.userScore}">
						-
					</c:when>
								<c:otherwise>
									<c:out value="${map.userScore}" />
								</c:otherwise>
							</c:choose>
							/
							<c:out value="${map.bestScore}" />
							<i class="fa fa-cog"></i>
						</div>
						<div title='<spring:message code="web.content.play.battery_level" />' >
							<c:choose>
								<c:when test="${empty map.userBatteryLevel}">
							-
						</c:when>
								<c:otherwise>
									<c:out value="${map.userBatteryLevel}" />
								</c:otherwise>
							</c:choose>
							/
							<c:out value="${map.bestBatteryLevel}" />
							<i class="fa fa-bolt"></i>
						</div>
						<c:if test="${not empty map.finished and map.finished == true}">
							<div title='<spring:message code="web.content.play.rank_position" />'>
								<c:out value="${map.mapRankingInfo.userPosition}"></c:out>
								/
								<c:out value="${map.mapRankingInfo.total}"></c:out>
								<i class="fa fa-trophy"></i>
							</div>
						</c:if>
					</div>
					<div style="width:32%; text-align:center; display: inline-block; vertical-align: middle;">
						<c:url value="/rank/${map.gameUrl}" var="rankUrl"/>
						<a href="${rankUrl}" class="btn btn-warning">
							<spring:message code="web.content.play.rank.label" />
						</a>
					</div>
					<div style="width: 32%; height:70px; display: inline-block; text-align:right; color: lightgreen; vertical-align: middle;">
						<c:if test="${not empty map.finished and map.finished == true}">
							<i title='<spring:message code="web.content.play.finished.alt" />' class="fa fa-check-circle fa-5x"></i>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<c:forEach begin="1" end="${blockedMapCount}" varStatus="loop">
		<div class="col-xs-6 col-md-4">
			<div class="game-thumbnail-div">
				<c:url value="/resources/images/content/play/map_blocked.png" var="blockedImg" />
				<img src="${blockedImg}">
			</div>
		</div>
	</c:forEach>
</div>


<!-- </div> -->