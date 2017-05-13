package pl.yeloon.magisterium.resolver.evaluator;

public enum ActionType {
	MOVE(5),
	JUMP(10),
	TURN_LEFT(5),
	TURN_RIGHT(5);
	
	private int batteryCost;
	private ActionType(int cost){
		this.batteryCost=cost;
	}
	public int getBatteryCost(){
		return this.batteryCost;
	}
}
