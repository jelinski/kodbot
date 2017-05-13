package pl.yeloon.magisterium.resolver.simulator.element;

public class Battery extends Element{
	
	public static int LOW_AMOUNT = 25;
	public static int MEDIUM_AMOUNT = 50;
	public static int HIGH_AMOUNT = 75;
	
	private int amount;
	
	public Battery(int amount) {
		this.amount = amount;
	}
	
	public int getBatteryAmount(){
		return this.amount;
	}
	
}
