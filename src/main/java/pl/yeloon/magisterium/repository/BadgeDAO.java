package pl.yeloon.magisterium.repository;

import pl.yeloon.magisterium.model.Badge;

public interface BadgeDAO {
	Badge getById(int id);

	long getBadgeCount();
}
