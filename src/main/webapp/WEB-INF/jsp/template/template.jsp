<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html prefix="og: http://ogp.me/ns">
<head>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
<title>KodBot - Krzysztof Jeliński. Toruń 2014</title>
<link rel="icon" type="image/png" href="<c:url value="/resources/images/icon.png" />"/>
<meta property="og:title" content="KodBot" />
<meta property="og:type" content="game" />
<meta property="og:description" content="Gra ucząca podstaw programowania i algorytmiki." />
<meta property="og:url" content="https://kodbot-yeloon.rhcloud.com/" />
<meta property="og:image" content="http://kodbot-yeloon.rhcloud.com/resources/images/header/logo.png" />
<meta property="og:image:secure_url" content="https://kodbot-yeloon.rhcloud.com/resources/images/header/logo.png" />

<!-- <meta http-equiv="Content-Type" content="text/html" charset="utf-8"> -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="description" content="Oficjalna strona gry KodBot">

<%-- 	<link rel="icon" href="<c:url value="/static/images/favicon.ico"/>" type="image/x-icon" /> --%>
<%-- 	<link rel="shortcut icon" href="<c:url value="/static/images/favicon.ico"/>" type="image/x-icon" /> --%>

<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/webjars/font-awesome/4.2.0/css/font-awesome.css" />">
<link href="<c:url value="/resources/css/fonts.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/template.css"/>" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<c:url value="/webjars/jquery/1.11.1/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/facebook.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cookieseu.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>

</head>
<body>

	<tiles:insertAttribute name="modals" />

	<div itemscope itemtype="http://schema.org/SoftwareApplication" class="container" id="mainContainer">

		<div id="header" class="row">
			<tiles:insertAttribute name="header" />
		</div>

		<div id="content" class="row">
			<div class="col-xs-12">
				<tiles:insertAttribute name="content" />
			</div>
		</div>

		<div id="footer" class="row">
			<tiles:insertAttribute name="footer" />
		</div>

	</div>

	<%-- GOOGLE ANALYTICS --%>
	<script>
        $(function () {
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                        (i[r].q = i[r].q || []).push(arguments)
                    }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-56721322-1', 'auto');
            ga('require', 'displayfeatures');
            ga('send', 'pageview');
        });
	</script>

	<%-- COOKIE.EU --%>
	<script>
        $(function () {
            jQuery.fn.cookiesEU({
                parent: $('#header'),
            });
        });
	</script>
</body>
</html>