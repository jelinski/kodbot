
  function statusChangeCallback(response) {
    if (response.status === 'connected') {
    	console.log('conected');
    } else if (response.status === 'not_authorized') {
    	console.log('not authorized');
    } else {
    	console.log('unknown');
    }
  }

  window.fbAsyncInit = function() {
	  FB.init({
	    appId      : '663978547017773',
	    cookie     : true,
	    xfbml      : true, 
	    version    : 'v2.1'
	  });
	
	//  FB.getLoginStatus(function(response) {
	//    statusChangeCallback(response);
	//  });
	
  };

  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  function testAPI() {
    FB.api('/me', function(response) {
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!';
    });
  }
  
  function loginFacebook(){
	  FB.login(function(response){
		  $.post('/magisterium/login_facebook', {token:response.authResponse.accessToken}, function(response){
			  console.log(response);
		  },'json') ;
	  },{scope: 'public_profile,email,user_friends'});
  }