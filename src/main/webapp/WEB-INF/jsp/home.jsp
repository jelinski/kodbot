<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row feature-row green-row" style="padding: 15px 0px">
	<div class="col-md-6" style="padding:30px">
		<h1><spring:message code="web.content.features.main.title"/></h1>
		<p><spring:message code="web.content.features.main.text"/></p>
	</div>
 <div class="col-md-6">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="box-shadow: rgb(63, 75, 45) 0px 0px 30px 0px;">
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			</ol>

			<div class="carousel-inner">
				<div class="item">
					<img src="<c:url value="/resources/images/content/main/carousel3.png" />" alt="Carousel 3">
				</div>
				<div class="item active">
					<a target="_blank" href="http://www.fizyka.umk.pl">
					<img itemprop="screenshot" src="<c:url value="/resources/images/content/main/carousel1.png" />" alt="Carousel 1">
					</a>
				</div>
			</div>

			<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
			</a>
			<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</div>
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
				<spring:message code="web.content.features.free.title" />
			</h1>
			<p>
				<spring:message code="web.content.features.free.text" />
			</p>
		</div>
		<div style="margin-top:50px" class="col-sm-12">
			<p><spring:message code="web.content.features.free.support" /></p>
			<div>
				<script type='text/javascript' src='https://ads.qadservice.com/t?id=fc3e8fa3-f6e2-4251-a96e-6e814a2a020c&size=300x250'></script>
			</div> 
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
				<spring:message code="web.content.features.programming.title" />
			</h1>
			<p>
				<spring:message code="web.content.features.programming.text" />
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
				<spring:message code="web.content.features.social.title" />
			</h1>
			<p>
				<spring:message code="web.content.features.social.text" />
			</p>
		</div>
	</div>
</section>

<!-- FACEBOOK -->
<div class="row feature-row" style="border-bottom: #34302d dashed 5px">
		<img style="width: 100%" src="<c:url value="/resources/images/content/main/arrow_${lang}.svg" />" alt="Like us on Facebook" />
</div>