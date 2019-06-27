<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
<link href="<c:url value="/resources/css/fonts.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/game.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/createjs-2013.12.12.min.js"/>"></script>
<script src="<c:url value="/resources/codemirror-4.6/lib/codemirror.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/codemirror-4.6/lib/codemirror.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/codemirror-4.6/theme/monokai.css"/>">
<script src="<c:url value="/resources/codemirror-4.6/mode/kodbot/kodbot.js"/>"></script>
<link href='https://fonts.googleapis.com/css?family=Press+Start+2P' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>
<link rel="icon" type="image/png" href="<c:url value="/resources/images/icon.png" />"/>
</head>

<body>
	<c:url value="/game/resolve/code" var="resolveUrl" />
	<c:url value="/game/fetchMap" var="fetchUrl" />
	<c:url value="/game/" var="gameUrl" />

	<input type="hidden" id="accessToken" value="${accessToken}" />
	<input type="hidden" id="mapKey" value="${mapKey}" />

	<div id="main-div">
		<div id="game-div" class="container-div">
			<div style="position: absolute; background-color: gray">
				<canvas id="gameCanvas">
					Twoja przegladarka nie obsluguje Canvas. Zalecamy pobrać przeglądarkę Firefox https://www.mozilla.org/pl/firefox/new/.
				</canvas>
			</div>
			<div id="map-slide-left-container" style="width: 32px; height: 100%; position: absolute; visibility:hidden">
				<img style="cursor: pointer; top: 279px; position: absolute" id="map-slide-previous-button" src="<c:url value="/resources/images/game/previous.png" />" alt="left arrow" />
			</div>
			<div id="map-slide-right-container" style="width: 32px; height: 100%; position: absolute; right:0px; visibility:hidden">
				<img style="cursor: pointer; top: 279px; position: absolute; right:0px" id="map-slide-next-button" src="<c:url value="/resources/images/game/next.png" />" alt="right arrow" />
				<img style="cursor: pointer; top: 279px; position: absolute; right:0px" id="map-slide-close-button" src="<c:url value="/resources/images/game/cancel.png" />"  alt="cross" />
			</div>
			
			<div id="game-overlay">
				<i class="fa fa-cog fa-5x"></i> <span id="command-counter">1</span>
			</div>
			<div id="error-message-container">
				<p id="error-message"></p>
			</div>
			<div id="score-container">
				<h1 style="text-align: center">Gratulacje!</h1>

				<h4 id="score-new" style="text-align: center">
					Twój wynik dla tej planszy to:
                    <i class="fa fa-cog"></i><span id="score-command-new" style="margin-right:10px"></span>
                    <i class="fa fa-bolt"></i><span id="score-battery-new"></span>
				</h4>
				<div style="text-align: center">
					<a id="next-map-button" class="btn btn-success">GO TO NEXT LEVEL</a>
				</div>
				<h3>Statystyki:</h3>
				<table>
					<tr>
						<td style="width:300px">move:</td>
						<td class="stat-col"><span id="stat-move-new"></span></td>
					</tr>
					<tr class="even">
						<td>jump:</td>
						<td class="stat-col"><span id="stat-jump-new"></span></td>
					</tr>
					<tr>
						<td>left:</td>
						<td class="stat-col"><span id="stat-left-new"></span></td>
					</tr>
					<tr class="even">
						<td>right:</td>
						<td class="stat-col"><span id="stat-right-new"></span></td>
					</tr>
					<tr>
						<td>assign:</td>
						<td class="stat-col"><span id="stat-assign-new"></span></td>
					</tr>
					<tr class="even">
						<td>assign with addition:</td>
						<td class="stat-col"><span id="stat-assign-with-addition-new"></span></td>
					</tr>
					<tr>
						<td>assign with subtraction:</td>
						<td class="stat-col"><span id="stat-assign-with-subtraction-new"></span></td>
					</tr>
					<tr class="even">
						<td>increment:</td>
						<td class="stat-col"><span id="stat-increment-new"></span></td>
					</tr>
					<tr>
						<td>decrement:</td>
						<td class="stat-col"><span id="stat-decrement-new"></span></td>
					</tr>
					<tr class="even">
						<td>repeat:</td>
						<td class="stat-col"><span id="stat-repeat-new"></span></td>
					</tr>
					<tr>
						<td>function definitions:</td>
						<td class="stat-col"><span id="stat-function-definitions-new"></span></td>
					</tr>
					<tr class="even">
						<td>function calls:</td>
						<td class="stat-col"><span id="stat-function-calls-new"></span></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="editor-div" class="container-div">
			<div style="width: 100%; height: 100%" id="codemirror-div"> </div>
			<div id="instruction-help-overlay" style="top: -285px; position: absolute; right: 0px; color: white; background-color: rgba(0, 0, 0, 0.74); z-index:2; width: 200px; height: 300px; overflow: hidden;">
				<div style="padding:10px">
					<p style="margin-bottom: 5px"><b>Instrukcje pojedyncze:</b></p>
					<c:url var="helpUrl" value="/help" />
					<p><a target="_blank" href="${helpUrl}#move" class="cm-keyword">move</a><span class="cost-info"> - 5 <i class="fa fa-bolt"></i></span></p>
					<p><a target="_blank" href="${helpUrl}#jump" class="cm-keyword">jump</a><span class="cost-info"> - 10 <i class="fa fa-bolt"></i></span></p>
					<p><a target="_blank" href="${helpUrl}#left" class="cm-keyword">left</a><span class="cost-info"> - 5 <i class="fa fa-bolt"></i></span></p>
					<p><a target="_blank" href="${helpUrl}#right" class="cm-keyword">right</a><span class="cost-info"> - 5 <i class="fa fa-bolt"></i></span></p>
					<p><a target="_blank" href="${helpUrl}#variable" class="cm-variable">zmienne</a></p>
					<p><a target="_blank" href="${helpUrl}#function-call" class="cm-function">wywołania funkcji</a></p>
					<p><a target="_blank" href="${helpUrl}#operators" style="color:white">operatory</a></p>
					<br/>
					<p style="margin-bottom: 5px"><b>Instrukcje blokowe:</b></p>
					<p><a target="_blank" href="${helpUrl}#main" class="cm-main">main</a></p>
					<p><a target="_blank" href="${helpUrl}#repeat" class="cm-keyword">repeat</a></p>
					<p><a target="_blank" href="${helpUrl}#functions" class="cm-function">definicje funkcji</a></p>
				</div>
				<div style="width: 100%; text-align: center; bottom: 1px; position: absolute;">
					<div style="cursor: pointer" id="instruction-help-up"><i class="fa fa-chevron-up"></i></div>
					<div style="cursor: pointer" id="instruction-help-down"><i class="fa fa-chevron-down"></i></div>
				</div>
			</div>
		</div>
		<div id="controls-div">
			<a style="width: 100px" class="btn btn-danger" href="<c:url value="/play"/>">Exit</a>
			<span id="game-speed-modes-div" style="visibility: hidden; margin-right:220px">
				<span style="color: white; margin-left: 50px; margin-right: 10px">
					<b>Speed mode:</b>
				</span>
				<a id="game-speed-fast-button" class="btn btn-default">FAST</a>
				<a id="game-speed-slow-button" class="btn btn-default btn-primary">SLOW</a>
				<a id="game-speed-step-button" class="btn btn-default">STEP</a>
			</span>
			
			<button style="width: 40px" id="sound-toggle-button" class="btn btn-primary"><i class="fa fa-volume-up"></i></button>
			
			<div class="pull-right">
				<a id="game-next-step-button" class="btn btn-success">NEXT STEP</a>
			<button style="width: 100px" id="play-button" class="btn btn-success">Play!</button>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="<c:url value="/resources/js/game.js" />"></script>
	<script type="text/javascript">
		var fetchUrl = '${fetchUrl}';
		var gameUrl = '${gameUrl}';
		var resolveUrl= '${resolveUrl}';
    </script>
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-56721322-1', 'auto');
  ga('require', 'displayfeatures');
  ga('send', 'pageview');

</script>
</body>
</html>