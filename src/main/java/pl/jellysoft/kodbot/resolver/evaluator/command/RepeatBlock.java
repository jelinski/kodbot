package pl.jellysoft.kodbot.resolver.evaluator.command;

public class RepeatBlock extends Block {
	public static final String KEYWORD = "repeat";
	public static final String COUNT_KEYWORD = "count";
	public static final String COMMANDS_KEYWORD = "commands";
	
	private String count;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
