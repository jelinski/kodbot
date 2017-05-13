package pl.yeloon.magisterium.resolver.evaluator.command;

public class FunctionCallCommand extends Command {
	public static final String KEYWORD = "functionCall";
	public static final String NAME_KEYWORD = "name";
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
