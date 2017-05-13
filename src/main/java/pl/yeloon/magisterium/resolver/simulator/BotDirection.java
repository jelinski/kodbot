package pl.yeloon.magisterium.resolver.simulator;

public enum BotDirection {
	BOTTOM_RIGHT(0),
	BOTTOM_LEFT(1),
	TOP_LEFT(2),
	TOP_RIGHT(3);
	
	private int id;
	
	BotDirection(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
}
