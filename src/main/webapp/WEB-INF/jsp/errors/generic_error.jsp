<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="col-sm-6 vcenter">
		<img alt="Page not found" src='<c:url value="/resources/images/content/error/ghost.png" />' style="width:75%" />
	</div><!--
  --><div class="col-sm-6 vcenter">
	<p class="h1">${errorHeader}</p>
	<p class="h2">${errorMessage}</p>
	</div>
</div>