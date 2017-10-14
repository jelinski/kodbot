<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row" style="padding: 50px 0px">
	<div class="col-xs-5 col-xs-offset-2 vcenter">
	
		<c:url value="/remind-password" var="remindPasswordUrl"/>
		<form:form action="${remindPasswordUrl}" modelAttribute="remindPasswordBean" method="POST">
			<div class="form-group">
				<form:label path="email">
					<spring:message code="web.register.email.label" />
				</form:label>
				<br />
				<form:errors path="email" cssClass="error" />
				<spring:message code="web.register.email.placeholder" var="emailPlaceholder" />
				<form:input id="email" path="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}" required="requited" placeholder="${emailPlaceholder}" class="form-control" />
			</div>
	
			<button class="btn btn-lg btn-success pull-right">
				<spring:message code="web.remindPassword.submit_button.label" />
			</button>
		</form:form>
	</div><!-- 
	 --><div class="col-xs-4 col-xs-offset-1 vcenter">
		<img src="<c:url value="/resources/images/content/remind_password/remind_password.png" />" alt="Remind password" />
	</div>
</div>