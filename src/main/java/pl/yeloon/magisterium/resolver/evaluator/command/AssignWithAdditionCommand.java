package pl.yeloon.magisterium.resolver.evaluator.command;

public final class AssignWithAdditionCommand extends Command {
	public static final String KEYWORD = "assignWithAddition";
	public static final String LEFT_OPERAND_KEYWORD = "leftOperand";
	public static final String FIRST_RIGHT_OPERAND_KEYWORD = "firstRightOperand";
	public static final String SECOND_RIGHT_OPERAND_KEYWORD = "secondRightOperand";

	private String leftOperand;
	private String firstRightOperand;
	private String secondRightOperand;

	public String getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(String leftOperand) {
		this.leftOperand = leftOperand;
	}

	public String getFirstRightOperand() {
		return firstRightOperand;
	}

	public void setFirstRightOperand(String firstRightOperand) {
		this.firstRightOperand = firstRightOperand;
	}

	public String getSecondRightOperand() {
		return secondRightOperand;
	}

	public void setSecondRightOperand(String secondRightOperand) {
		this.secondRightOperand = secondRightOperand;
	}

}
