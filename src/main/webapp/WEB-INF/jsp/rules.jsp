<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<h1>
    <spring:message code="goal.title"/>
</h1>
<p>
    <spring:message code="goal.description"/>
</p>

<h1>
    <spring:message code="rules.title"/>
</h1>
<p>
    <spring:message code="rules.description.general"/>
</p>
<div>
    <img src="${pageContext.request.contextPath}/resources/images/content/rules/batteryFull.png"/>
</div>
<p>
    <spring:message code="rules.description.howToGetEnergy"/>
</p>
<div>
    <img src="${pageContext.request.contextPath}/resources/images/content/rules/batteryLow.png"/>
    <spring:message code="rules.description.batery25"/>
</div>
<div>
    <img src="${pageContext.request.contextPath}/resources/images/content/rules/batteryMedium.png"/>
    <spring:message code="rules.description.batery50"/>
</div>
<div>
    <img src="${pageContext.request.contextPath}/resources/images/content/rules/batteryHigh.png"/>
    <spring:message code="rules.description.batery75"/>
</div>