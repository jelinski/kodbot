<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row feature-row green-row" style="padding: 15px 0px">
	<div class="col-sm-4" style="margin-top: 35px">
		<i class="fa fa-cubes" aria-hidden="true" style="font-size: 215px"></i>
	</div>
	<div class="col-sm-8" style="padding:30px">
		<h1><spring:message code="home.features.main.title"/></h1>
		<p><spring:message code="home.features.main.text"/></p>
	</div>
</div>

<!-- FEATURES -->
<section>
	<div class="row feature-row" style="padding: 75px 15px; border-bottom: #6db33f dashed 5px; border-top: #6db33f dashed 5px">
		<div class="col-sm-4 col-sm-push-8 vcenter">
			<img src="<c:url value="/resources/images/content/main/money.png" />" alt="Money" style="height: 200px">
		</div><!--
	 --><div class="col-sm-8 col-sm-pull-4 vcenter">
			<h1>
				<spring:message code="home.features.free.title" />
			</h1>
			<p>
				<spring:message code="home.features.free.text" />
			</p>
		</div>
	</div>
</section>

<section>
	<div class="row feature-row green-row" style="padding: 75px 15px;">
		<div class="col-sm-4 vcenter">
			<img src="<c:url value="/resources/images/content/main/programming.png" />" alt="Free" style="height: 200px">
		</div><!--
	--><div class="col-sm-8 vcenter">
			<h1>
				<spring:message code="home.features.programming.title" />
			</h1>
			<p>
				<spring:message code="home.programming.text" />
			</p>
		</div>
	</div>
</section>

<section>
	<div class="row feature-row" style="padding: 50px 15px; border-top-style: dashed; border-top-width: 5px; border-top-color: #6db33f;">
		<div class="col-sm-4 col-sm-push-8 vcenter">
			<img src="<c:url value="/resources/images/content/main/social.png" />" alt="Social" style="height: 200px">
		</div><!--
	--><div class="col-sm-8 col-sm-pull-4 vcenter">
			<h1>
				<spring:message code="home.features.social.title" />
			</h1>
			<p>
				<spring:message code="home.features.social.text" />
			</p>
		</div>
	</div>
</section>

<!-- FACEBOOK -->
<div class="row feature-row" style="border-bottom: #34302d dashed 5px">
		<img style="width: 100%" src="<c:url value="/resources/images/content/main/arrow_${lang}.svg" />" alt="Like us on Facebook" />
</div>