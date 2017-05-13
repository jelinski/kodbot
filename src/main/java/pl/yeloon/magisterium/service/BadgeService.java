package pl.yeloon.magisterium.service;

import pl.yeloon.magisterium.model.Badge;
import pl.yeloon.magisterium.resolver.statistic.StatisticDTO;

public interface BadgeService {

	public static final int FACEBOOK = 1;
	public static final int INVITED_1_FRIEND = 17;
	public static final int INVITED_3_FRIEND = 18;
	public static final int INVITED_5_FRIEND = 19;
	public static final int INVITED_10_FRIEND = 20;
	public static final int REGISTERD_1_FRIEND = 21;
	public static final int REGISTERD_3_FRIEND = 22;
	public static final int REGISTERD_5_FRIEND = 23;
	public static final int FINISHED_5_MAPS = 2;
	public static final int FINISHED_10_MAPS = 3;
	public static final int FINISHED_15_MAPS = 4;
	public static final int TOTAL_10_COMMANDS = 7;
	public static final int TOTAL_50_COMMANDS = 8;
	public static final int TOTAL_100_COMMANDS = 9;
	public static final int TOTAL_200_COMMANDS = 10;
	public static final int TOTAL_500_COMMANDS = 11;
	public static final int TOTAL_1000_COMMANDS = 12;
	
	Badge getById(int id);

	long getBadgeCount();

	void assignBadgeToUserForFriendRegistering(int userId);

	void assignBadgeToUserForFriendInvitating(int userId);

	void assignBadgesForStatistics(int userId, StatisticDTO oldStatistics, StatisticDTO newStatistics);
	
}
