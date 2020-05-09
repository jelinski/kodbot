<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html prefix="og: http://ogp.me/ns" lang="en">
<head>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-116483909-2"></script>
<script>
	window.dataLayer = window.dataLayer || [];
	function gtag(){dataLayer.push(arguments);}
	gtag('js', new Date());

	gtag('config', 'UA-116483909-2');
</script>

<title>KodBot - Krzysztof Jeliński. Toruń 2014</title>
<link rel="icon" type="image/png" href="<c:url value="/resources/images/icon.png" />"/>
<meta property="og:title" content="KodBot" />
<meta property="og:type" content="game" />
<meta property="og:description" content="Gra ucząca podstaw programowania i algorytmiki." />
<meta property="og:url" content="https://kodbot.jellysoft.pl/" />
<meta property="og:image" content="http://kodbot.jellysoft.pl/resources/images/header/logo.png" />
<meta property="og:image:secure_url" content="https://kodbot.jellysoft.pl/resources/images/header/logo.png" />

<!-- <meta http-equiv="Content-Type" content="text/html" charset="utf-8"> -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="description" content="Oficjalna strona gry KodBot">

<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
<link href="<c:url value="/resources/css/fonts.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/template.css"/>" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cookieseu.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>

</head>
<body>

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

	<%-- COOKIE.EU --%>
	<script>
        $(function () {
            jQuery.fn.cookiesEU({
                parent: $('#header')
            });
        });
	</script>
</body>
</html>
