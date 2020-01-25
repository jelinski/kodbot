package pl.jellysoft.kodbot.resolver.simulator.element;

public enum ElementType {
	HEAVY_BOX(1), LIGHT_BOX(2), SPIKED_BOX(3), BATTERY_LOW(100), BATTERY_MEDIUM(101), BATTERY_HIGH(102);

	private int id;

	private ElementType(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public ElementType getTypeById(int id) {
		for (ElementType e : ElementType.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
}
