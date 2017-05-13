<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/game/${mapKey}" var="gameUrl"/>
<div style="margin-bottom: 20px;">
<c:choose>
	<c:when test="${not empty scores}">
	<div style="text-align: center; height: 350px; padding-top: 25px;">
		<div style="width:300px; display:inline-block">
			<table style="text-align:left">
				<tr>
				<th style="width:200px">Początkowy poziom baterii</th>
				<td>${startBattery}<i class="fa fa-bolt"></i></td>
				</tr>
				<tr>
				<th>Najlepszy wynik</th>
				<td>${bestCommand}<i class="fa fa-cog"></i> ${bestBattery}<i class="fa fa-bolt"></i></td>
				</tr>
			</table>
			<div style="margin-top: 30px"><a href="${gameUrl}" class="btn-lg btn-success">Graj</a></div>
		</div>
		<div style="width:300px; display:inline-block;">
			<c:url value="/resources/images/content/play/${mapKey}.png" var="imageUrl" />
			<img style="box-shadow: 0px 0px 64px black;" src="${imageUrl}"/>
		</div>
	</div>
		<table style="width: 100%">
			<tr style="height: 35px">
				<th style="width: 70px; text-align: center">#</th>
				<th>Nick</th>
				<th>Command counter</th>
				<th>Battery level</th>
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
					<td>${score.commandCounter}</td>
					<td>${score.batteryLevel}</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<div style="height: 600px; text-align: center; padding-top: 250px;">
			<span class="h2">Ta plansza nie posiada jeszcze żadnych wyników. Bądź pierwszym, który ją ukończy!</span>
			<div style="margin-top: 30px"><a href="${gameUrl}" class="btn-lg btn-success">Graj</a></div>
		</div>
	</c:otherwise>
</c:choose>
</div>