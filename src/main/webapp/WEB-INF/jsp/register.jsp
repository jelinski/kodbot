<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row" style="padding: 50px 0px">
	<div class="col-xs-5 col-xs-offset-2 vcenter">
		<div style="width:100%; text-align: center">
			<form action="<c:url value="/auth/facebook"/>" method="POST">
				<input type="hidden" name="scope" value=public_profile,user_friends,email />
				<button style="margin-top: 30px" class="btn btn-lg btn-primary" type="submit">
					<span>
						<i class="fa fa-facebook-square fa-lg"></i>
					</span>
					<span style="vertical-align: middle;">Zarejestruj się przez Facebooka</span>
				</button>
			</form>
			<p class="h3">LUB</p>
		</div>
		<c:url value="/register/new_user" var="registerUrl" />
		<form:form action="${registerUrl}" modelAttribute="registerUserBean" method="POST">
			<div class="form-group">
				<form:label path="email">
					<spring:message code="web.register.email.label" />
				</form:label>
				<br />
				<form:errors path="email" cssClass="error" />
				<spring:message code="web.register.email.placeholder" var="emailPlaceholder" />
				<form:input id="email" path="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}" required="requited" placeholder="${emailPlaceholder}" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="nickname">
					<spring:message code="web.register.nickname.label" />
				</form:label>
				<br />
				<form:errors path="nickname" cssClass="error" />
				<spring:message code="web.register.nickname.placeholder" var="nicknamePlaceholder" />
				<form:input id="nickname" path="nickname" pattern="[a-zA-Z0-9-_ ]{4,}" required="required" placeholder="${nicknamePlaceholder}" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="password">
					<spring:message code="web.register.password.label" />
				</form:label>
				<br />
				<form:errors path="password" cssClass="error" />
				<spring:message code="web.register.password.placeholder" var="passwordPlaceholder" />
				<form:password path="password" required="required" placeholder="${passwordPlaceholder}" class="form-control" />
			</div>
			<div class="form-group">
				<label><spring:message code="web.register.repeated_password.label" /></label> <br />
				<form:errors path="repeatedPassword" cssClass="error" />
				<spring:message code="web.register.repeated_password.placeholder" var="repeatedPasswordPlaceholder" />
				<form:password path="repeatedPassword" required="required" placeholder="${repeatedPasswordPlaceholder}" class="form-control" />
			</div>
			<div class="form-group">
				<form:checkbox path="acceptRegulation" />
				<label><spring:message code="web.register.regulation.accept" /> <a target="_blank" href='<c:url value="/terms_of_use"/>'><spring:message code="web.register.regulation.link_label" /></a></label> <br />
				<form:errors path="acceptRegulation" cssClass="error" />
			</div>
			<form:hidden path="registrationCode" />
			<button class="btn btn-lg btn-success pull-right" type="submit">
				<spring:message code="web.register.submit_button.label" />
				<i class="fa fa-sign-in fa-lg"></i>
			</button>
		</form:form>
	</div><!-- 
	 --><div class="col-xs-4 col-xs-offset-1 vcenter">
		<img src="<c:url value="/resources/images/content/register/register.png" />" alt="Register" />
	</div>
</div>

<script type="text/javascript">
	$('#email').change(function() {
		var nick = $('#email').val();
		nick = nick.split('@')[0];
		$('#nickname').val(nick);
	});
</script>