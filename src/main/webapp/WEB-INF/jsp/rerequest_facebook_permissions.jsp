<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

Nie mozna utworzyc konta, poniewaz nie przyznano wszystkich uprawnien. KodBot nigdy nie bedzie publikowal <b>zadnych</b> postow na Twojej tablicy oraz nie bedzie udostepniaL pozyskanych danych.<br/><br/>
Udostepnienie emaila jest konieczne ze wzgledu na to, iz stanowi on login w aplikacji KodBot.

<c:url value="/connect/facebook" var="facebookConnectUrl" />
		<form action="${facebookConnectUrl}" method="POST">
				<input type="hidden" name="scope" value="public_profile,user_friends,email" />
				<input type="hidden" name="auth_type" value="rerequest"  />
				<p><button type="submit">Connect to Facebook</button></p>
			</form>
