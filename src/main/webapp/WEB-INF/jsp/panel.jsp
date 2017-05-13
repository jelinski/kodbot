<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<c:url value="/panel/change-password" var="changePasswordUrl" />
<c:url value="/panel/change-nickname" var="changeNicknameUrl" />
<c:url value="/panel/invite-friend" var="inviteFriendUrl" />

<security:authorize access="isAuthenticated()">


	<!-- JESLI UZYTKOWNIK JESZCZE NIE POLACZYL SWOJEGO KONTA Z FACEBOOKIEM -->
	<c:if test="${!facebookConnected}">
		<div class="row">
			<div class="col-xs-offset-3 col-xs-6" style="text-align: center; margin-top: 50px; background-color: rgb(63, 109, 179); padding: 20px;">
				<h3>
					<spring:message code="web.panel.facebook.connect" />
				</h3>
				<c:url value="/connect/facebook" var="facebookConnectUrl" />
				<form action="${facebookConnectUrl}" method="POST">
					<input type="hidden" name="scope" value="public_profile, user_friends, email" />
					<div class="formInfo">
						<p>
							<spring:message code="web.panel.facebook.description" />
						</p>
					</div>
					<button class="btn btn-info" type="submit">
						<i class="fa fa-facebook-square fa-lg"></i> <span style="vertical-align: middle"><spring:message code="web.panel.facebook.connect.button" /></span>
					</button>
				</form>
			</div>
		</div>
	</c:if>


	<div class="row">
		<div class="col-sm-6" style="padding: 75px">
			<div style="text-align: center">
				<i class="fa fa-users fa-5x"></i>
				<h3>Zaproś swoich znajomych i odblokuj dodatkowe osiągnięcia</h3>
			</div>
			<div class="form-group">
				<label for=friendEmail>Email znajomego: </label> <input id="friend-email" class="form-control" type="text" placeholder="email" />
			</div>
			<input id="invite-friend-button" class="btn btn-primary pull-right" type="button" value="Zaproś">
			<br/>
			<br/>
			<div style="text-align: center">
				<i class="fa fa-trophy fa-5x"></i>
				<h3>Zobacz ranking najlepszych graczy</h3>
				<c:url value="/overall-rank" var="overallRankingUrl"/>
				<a class="btn btn-success" href="${overallRankingUrl}"><i class="fa fa-list-ol"></i> Przejdź do rankingu</a>
			</div>
			
		</div>
		<div class="col-sm-6" style="padding: 75px">
			<div style="text-align: center">
				<i class="fa fa-pencil-square-o fa-5x"></i>
				<h3>Zmień nazwę użytkownika</h3>
			</div>

			<div class="form-group">
				<label for="username">Nazwa użytkownika:</label> <input id="username" class="form-control" type="text" value='<c:out value="${nickname}"/>' placeholder="nick">
			</div>
			<input id="change-username-button" class="btn btn-primary pull-right" type="button" value="Change username"> <br />

			<div class="form-group">
				<h3>Zmień hasło</h3>
				<div class="form-group">
					<label for="current-password">Obecne hasło:</label> <input id="current-password" class="form-control" type="password">
				</div>
				<div class="form-group">
					<label for="new-password">Nowe hasło:</label> <input id="new-password" class="form-control" type="password">
				</div>
				<div class="form-group">
					<label for="repeated-new-password">Powtórz nowe hasło:</label> <input id="repeated-new-password" class="form-control" type="password">
				</div>
				<input id="change-password-button" class="btn btn-warning pull-right" type="button" value="Change password">
			</div>

		</div>
	</div>

	<h3>Odznaki:</h3>
	<div class="row" style="text-align: center">
		<c:forEach items="${badges}" var="badge">
			<div class="col-xs-4 col-lg-3">

				<c:url value="/resources/images/content/panel/${badge.imageUrl}" var="imageUrl" />
				<img src="${imageUrl}">
				<c:choose>
					<c:when test='${locale=="pl"}'>
						<h4>
							<c:out value="${badge.polishDescription}" />
						</h4>
					</c:when>
					<c:otherwise>
						<h4>
							<c:out value="${badge.englishDescription}" />
						</h4>
					</c:otherwise>
				</c:choose>
			</div>

		</c:forEach>

		<c:forEach begin="1" end="${unblockedBadgeCount}" varStatus="loop">
			<div class="col-xs-4 col-lg-3">
				<c:url value="/resources/images/content/panel/badge_unblocked.png" var="unblockedImg" />
				<img src="${unblockedImg}">
				<h4>
					<spring:message code="web.panel.badge.unblocked" />
				</h4>
			</div>
		</c:forEach>
	</div>

	<div style="margin: 10px 0px;">
		<h3>Statystyki:</h3>
		<p>Overal: ${statistic.overallScore}</p>
		<p>Move: ${statistic.moveCounter}</p>
		<p>Jump: ${statistic.jumpCounter}</p>
		<p>Left: ${statistic.turnLeftCounter}</p>
		<p>Right: ${statistic.turnRightCounter}</p>
		<p>Assign: ${statistic.assignCounter}</p>
		<p>Assign with addition: ${statistic.assignWithAdditionCounter}</p>
		<p>Assign with subtraction: ${statistic.assignWithSubtractionCounter}</p>
		<p>Increment: ${statistic.incrementCounter}</p>
		<p>Decrement: ${statistic.decrementCounter}</p>
		<p>Function definitions: ${statistic.definedFunctionCounter}</p>
		<p>Function calls: ${statistic.calledFunctionCounter}</p>
		<p>Repeat: ${statistic.repeatCounter}</p>
	</div>
</security:authorize>

<!-- 	W PRZYPADKU BRAKU AUTENTYKACJI WYSWIETL KOMUNIKAT O KONIECZNOSCI ZALOGOWANIA -->
<security:authorize access="isAnonymous()">
	<div style="height: 600px; text-align: center; padding-top: 300px;">
		<span class="h2"><spring:message code="web.content.panel.login.required" /></span>
	</div>
</security:authorize>

<script type="text/javascript">
	
	$('#change-password-button').click(function(){
		var oldPassword =  $('#current-password').val();
		var newPassword = $('#new-password').val();
		var repeatedNewPassword =  $('#repeated-new-password').val();
		if(oldPassword.length <6 || newPassword.length <6){
			showModal("Hasło musi mieć przynajmniej 6 znaków");
			return;
		}
		else if(newPassword != repeatedNewPassword){
			showModal("Podane hasła różnią się od siebie");
			return;
		}
		
		$.ajax({
			type : "POST",
			url : '${changePasswordUrl}',
			data : 
				JSON.stringify({
					"oldPassword" : oldPassword,
					"newPassword" : newPassword,
					"repeatedNewPassword" : repeatedNewPassword
			}),
			success : function(data) {
				showModal(data.status);
				$('#current-password').val('');
				$('#new-password').val('');
				$('#repeated-new-password').val('');
			},
			dataType : 'json',
			contentType: "application/json; charset=utf-8"
		});
	});

	$('#change-username-button').click(function(){
		var username = $('#username').val();
		if(username.length<4){
			showModal("Nazwa użytkownika musi składać się z przynajmniej 4 znaków");
			return;
		}

		if(username.match("[a-zA-Z0-9- _]{4,}")[0].length != username.length){
			showModal("Nazwa użytkownika zawiera niedozwolone symbole lub jest krótsza niż 4 znaki");
			return;
		}

		$.ajax({
			type : "POST",
			url : '${changeNicknameUrl}',
			data : 
				{
					"nickname" : username
				},
			success : function(data) {
				showModal(data.status);
			},
			dataType : 'json',
		});
	});

	$('#invite-friend-button').click(function(){
		var friendEmail = $('#friend-email').val();
		var matched = friendEmail.match("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");

		if(typeof matched == 'undefined' || matched == null || matched[0].length != friendEmail.length){
			showModal("Podany email jest nieprawidłowy");
			return;
		}

		$.ajax({
			type : "POST",
			url : '${inviteFriendUrl}',
			data : 
				{
					"friendEmail" : friendEmail
				},
			success : function(data) {
				showModal(data.status);
				$('#friend-email').val('');
			},
			dataType : 'json',
		});
	});
</script>
