<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="login-modal" class="modal fade">
	<div class="modal-dialog modal-vertical-centered">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><spring:message code="web.modal.login.title"/></h4>
			</div>

			<div style="text-align: center">
				<form action="<c:url value="/auth/facebook"/>" method="POST">
					<input type="hidden" name="scope" value=public_profile,user_friends,email />
					<button style="margin-top: 30px" class="btn btn-lg btn-primary" type="submit">
						<span> <i class="fa fa-facebook-square fa-lg"></i>
						</span> <span style="vertical-align: middle;"><spring:message code="web.modal.login.facebook.button.label"/></span>
					</button>
				</form>
				<p class="h3"><spring:message code="web.modal.login.or" /></p>
			</div>
			<form id="loginForm" name='loginForm' action="<c:url value='/loginProcess'/>" method='POST'>
				<div class="modal-body">
					<c:if test="${param.loginerror == 'true'}">
						<p class="error" style="text-transform: uppercase">
							<spring:message code="web.modal.login.invalid"/>
						</p>
					</c:if>
					<div class="form-group">
						<label for="usernameInput"> <spring:message code="web.modal.login.email.label" />
						</label> <input type="email" name="username" class="form-control" id="usernameInput" placeholder="<spring:message code="web.modal.login.email.placeholder"/>">
					</div>

					<div class="form-group">
						<label for="usernameInput"> <spring:message code="web.modal.login.password.label" />
						</label> <input type="password" name="password" class="form-control" id="passwordInput" placeholder="<spring:message code="web.modal.login.password.placeholder"/>">
					</div>
					
					<div class="form-group">
						<input id="remember-me-checkbox" type="checkbox" name="_spring_security_remember_me" />
						<label for="remember-me-checkbox">
							<spring:message code="web.modal.login.remember_me" />
						</label>
					</div>

					<input type="hidden" name="beforeurl" value="${requestScope['javax.servlet.forward.request_uri']}" /> <input class="btn btn-primary" name="submit" type="submit" value="<spring:message code="web.modal.login.button" />" />
				</div>
				<div class="modal-footer">
					<p>
						<spring:message code="web.modal.login.register.label" />
						-
						<a href="<c:url value="/register"/>">
							<spring:message code="web.modal.login.register.link" />
						</a>
					</p>
					<p>
						<spring:message code="web.modal.login.forgot.label" /> - <a href="<c:url value="/forgot-password" />"> <spring:message code="web.modal.login.forgot.link" /> </a>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>


<div id="custom-modal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<p id="custom-modal-paragraph"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
