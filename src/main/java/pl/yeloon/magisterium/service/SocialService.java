package pl.yeloon.magisterium.service;

import pl.yeloon.magisterium.model.PartnerCode;

public interface SocialService {

	void inviteFriend(String friendEmail);

	PartnerCode getPartnerCode(String registrationCode);

}
