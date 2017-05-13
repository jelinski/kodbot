package pl.yeloon.magisterium.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.yeloon.magisterium.model.Badge;
import pl.yeloon.magisterium.model.User;
import pl.yeloon.magisterium.repository.BadgeDAO;
import pl.yeloon.magisterium.repository.SocialDAO;
import pl.yeloon.magisterium.resolver.statistic.StatisticDTO;

@Service
public class BadgeServiceImpl implements BadgeService {

	@Autowired
	private BadgeDAO badgeDAO;

	@Autowired
	private SocialDAO socialDAO;

	@Autowired
	private UserService userService;

	@Autowired
	private StatisticService statisticService;

	@Override
	public Badge getById(int id) {
		return badgeDAO.getById(id);
	}

	@Override
	public long getBadgeCount() {
		return badgeDAO.getBadgeCount();
	}

	@Async
	@Override
	@Transactional
	public void assignBadgeToUserForFriendInvitating(int userId) {
		User user = userService.getUserById(userId);
		List<Badge> badgesToAssign = new ArrayList<Badge>();

		if (user != null) {
			long invitedFriends = countInvitedFriendsByUserWithId(userId);
			if (invitedFriends >= 1) {
				badgesToAssign.add(getById(BadgeService.INVITED_1_FRIEND));
				if (invitedFriends >= 3) {
					badgesToAssign.add(getById(BadgeService.INVITED_3_FRIEND));
					if (invitedFriends >= 5) {
						badgesToAssign.add(getById(BadgeService.INVITED_5_FRIEND));
						if (invitedFriends >= 10) {
							badgesToAssign.add(getById(BadgeService.INVITED_10_FRIEND));
						}
					}
				}
			}

			List<Badge> userBadges = user.getBadges();
			for (Badge b : badgesToAssign) {
				if (!userBadges.contains(b)) {
					userBadges.add(b);
				}
			}
			userService.saveUser(user);
		}
	}

	@Async
	@Override
	@Transactional
	public void assignBadgeToUserForFriendRegistering(int userId) {
		User user = userService.getUserById(userId);
		List<Badge> badgesToAssign = new ArrayList<Badge>();

		if (user != null) {
			long registeredFriends = countRegisteredFriendsByUserWithId(userId);
			if (registeredFriends >= 1) {
				badgesToAssign.add(getById(BadgeService.REGISTERD_1_FRIEND));
				if (registeredFriends >= 3) {
					badgesToAssign.add(getById(BadgeService.REGISTERD_3_FRIEND));
					if (registeredFriends >= 5) {
						badgesToAssign.add(getById(BadgeService.REGISTERD_5_FRIEND));
					}
				}
			}

			List<Badge> userBadges = user.getBadges();
			for (Badge b : badgesToAssign) {
				if (!userBadges.contains(b)) {
					userBadges.add(b);
				}
			}
			userService.saveUser(user);
		}
	}

	private long countInvitedFriendsByUserWithId(int userId) {
		return socialDAO.countInvitedFriendsByUserWithId(userId);
	}

	private long countRegisteredFriendsByUserWithId(int userId) {
		return socialDAO.countRegisteredFriendsByUserWithId(userId);
	}

	@Async
	@Override
	@Transactional
	public void assignBadgesForStatistics(int userId, StatisticDTO oldStatistics, StatisticDTO newStatistics) {
		User user = userService.getUserById(userId);
		if (user != null) {

			StatisticDTO sum = statisticService.addStatistics(oldStatistics, newStatistics);
			int totalCommands = sum.getAssignCount() + sum.getAssignWithAdditionCount() + sum.getAssignWithSubtractionCount() + sum.getCalledFunctionCount() + sum.getDecrementCount() + sum.getDefinedFunctionCount()
					+ sum.getIncrementCount() + sum.getJumpCount() + sum.getMoveCount() + sum.getRepeatCount() + sum.getTurnLeftCount() + sum.getTurnRightCount();
			List<Badge> badgesToAssign = new ArrayList<Badge>();
			int overall = sum.getOverall();
			if (overall >= 5) {
				badgesToAssign.add(getById(FINISHED_5_MAPS));
				if (overall >= 10) {
					badgesToAssign.add(getById(FINISHED_10_MAPS));
					if (overall >= 15) {
						badgesToAssign.add(getById(FINISHED_15_MAPS));
					}
				}
			}

			if (totalCommands >= 10) {
				badgesToAssign.add(getById(TOTAL_10_COMMANDS));
				if (totalCommands >= 50) {
					badgesToAssign.add(getById(TOTAL_50_COMMANDS));
					if (totalCommands >= 100) {
						badgesToAssign.add(getById(TOTAL_100_COMMANDS));
						if (totalCommands >= 200) {
							badgesToAssign.add(getById(TOTAL_200_COMMANDS));
							if (totalCommands >= 500) {
								badgesToAssign.add(getById(TOTAL_500_COMMANDS));
								if (totalCommands >= 1000) {
									badgesToAssign.add(getById(TOTAL_1000_COMMANDS));
								}
							}
						}
					}
				}
			}

			List<Badge> userBadges = user.getBadges();
			for (Badge b : badgesToAssign) {
				if (!userBadges.contains(b)) {
					userBadges.add(b);
				}
			}
			userService.saveUser(user);
		}

	}
}
