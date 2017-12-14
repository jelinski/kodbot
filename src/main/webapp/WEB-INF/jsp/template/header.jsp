<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 style="display: none">
    <span itemprop="name">KodBot</span>
</h1>

<div class="col-md-2" id="logo-container">
    <a id="logo" href="<c:url value="/" />">
        <meta itemprop="url" content="https://kodbot.yeloon.pl">
    </a>
    <img style="display: none" src="<c:url value="/resources/images/header/logo-hover.png" />">
</div>
<div class="col-md-10" style="text-align: right" id="menu-container">
    <nav>
        <div class="menu-entry">
            <a itemprop="downloadUrl" href="<c:url value="/play" />">
                <spring:message code="web.menu.play"/>
            </a>
        </div>
        <div class="menu-entry">
            <a href="<c:url value="/rules" />">
                <spring:message code="web.menu.rules"/>
            </a>
        </div>
        <div class="menu-entry">
            <a href="<c:url value="/help" />">
                <spring:message code="web.menu.help"/>
            </a>
        </div>
        <div id="lang-select" class="menu-entry"
             style="vertical-align: top; height: 66px; min-width: 30px;">
            <a class="menu-lang-entry" href="?lang=pl">
                <img alt="PL" src="<c:url value="/resources/images/header/lang_pl.png" />" height="15"/>
            </a>
            <a class="menu-lang-entry" href="?lang=en">
                <img alt="EN" src="<c:url value="/resources/images/header/lang_en.png" />" height="15"/>
            </a>
        </div>
    </nav>
</div>
