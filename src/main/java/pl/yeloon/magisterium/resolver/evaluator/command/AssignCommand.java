package pl.yeloon.magisterium.resolver.evaluator.command;

public class AssignCommand extends Command {
	public static final String KEYWORD = "assign";
	public static final String LEFT_OPERAND_KEYWORD = "leftOperand";
	public static final String RIGHT_OPERAND_KEYWORD = "rightOperand";
	
	private String leftOperand;
	private String rightOperand;

	public void setLeftOperand(String leftOperand) {
		this.leftOperand = leftOperand;
	}

	public void setRightOperand(String rightOperand) {
		this.rightOperand = rightOperand;
	}

	public String getLeftOperand() {
		return leftOperand;
	}

	public String getRightOperand() {
		return rightOperand;
	}

}
