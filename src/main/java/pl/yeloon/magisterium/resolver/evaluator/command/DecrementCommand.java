package pl.yeloon.magisterium.resolver.evaluator.command;

public final class DecrementCommand extends Command {
	public static final String KEYWORD = "decrement";
	public static final String VARIABLE_KEYWORD = "variable";

	private String variable;

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

}
