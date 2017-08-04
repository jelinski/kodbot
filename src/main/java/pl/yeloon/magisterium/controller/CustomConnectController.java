package pl.yeloon.magisterium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

import pl.yeloon.magisterium.security.CustomUserDetails;
import pl.yeloon.magisterium.service.BadgeService;
import pl.yeloon.magisterium.service.UserService;

public class CustomConnectController extends ConnectController {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Autowired
	private UserService userService;
	
	public CustomConnectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
		addInterceptor(new ConnectInterceptor<Facebook>(){

			@Override
			public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
			}

			@Override
			public void postConnect(Connection<Facebook> connection, WebRequest request) {
				handlePostConnect();
			}});
	}

	private static final String VIEW_NAME = "/panel";

	@Override
	protected String connectedView(String providerId) {
		return "redirect:" + VIEW_NAME;
	}

	@Override
	protected String connectView(String providerId) {
		return "redirect:/auth/facebook";
	}
	
	private void handlePostConnect(){
		if(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
			connectionRepository.removeConnections("facebook");
		}
		else{
			CustomUserDetails cud = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userService.addBadgeToProfile(cud.getId(), BadgeService.FACEBOOK);
		}
	}

}
