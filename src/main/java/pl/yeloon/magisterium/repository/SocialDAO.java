package pl.yeloon.magisterium.repository;

import pl.yeloon.magisterium.model.PartnerCode;

public interface SocialDAO {

	void savePartnerCode(PartnerCode partnerCode);

	PartnerCode getPartnerCode(String registrationCode);

	long countInvitedFriendsByUserWithId(int userId);

	long countRegisteredFriendsByUserWithId(int userId);

	PartnerCode getPartnerCodeByFriendEmail(String invitedFriendEmail);
	
}
