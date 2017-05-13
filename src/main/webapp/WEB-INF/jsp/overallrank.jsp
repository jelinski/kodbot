<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="margin-bottom: 20px;">
<c:choose>
	<c:when test="${not empty scores}">
		<h2>Ranking najlepszych graczy</h2>
		<table style="width: 100%; margin-top:20px">
			<tr style="height: 35px">
				<th style="width: 70px; text-align: center">#</th>
				<th>Nick</th>
				<th>Score</th>
			</tr>
			<c:forEach items="${scores}" var="score" varStatus="loop">
				<tr style="height:54px; ${loop.count mod 2 eq 1 ?"background-color:rgba(0,0,0,0.2)" :"a"}">
					<td style="width: 70px; text-align: center">${loop.count}</td>
					<td>
						<c:if test="${not empty score.imageUrl }">
							<c:set var="userImageUrl" value="${score.imageUrl}" />
						</c:if>
						<c:if test="${empty score.imageUrl}">
							<c:url var="userImageUrl" value="/resources/images/content/rank/placeholder.png" />
						</c:if>
						<img style="margin-right:10px" width="50" height="50" src="${userImageUrl}"/>
						<span style="width: 50px; margin-right: 10px" title="Ogólny wynik gracza" class="badge progress-bar-success">${score.overall}</span> 
						${score.username}
					</td>
					<td>${score.overallScore}</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<div style="height: 600px; text-align: center; padding-top: 250px;">
			<span class="h2">Nie ma jeszcze żadnych wyników. Ukończ kilka plansz i bądź pierwszy!</span>
			<c:url var="gamePanelUrl" value="/game" />
			<div style="margin-top: 30px"><a href="${gamePanelUrl}" class="btn-lg btn-success">Graj</a></div>
		</div>
	</c:otherwise>
</c:choose>
</div>