package pl.jellysoft.kodbot.resolver.evaluator.command;

public class FunctionBlock extends Block {
	public static final String KEYWORD = "function";
	public static final String NAME_KEYWORD = "name";
	public static final String COMMANDS_KEYWORD = "commands";
	
	private String name; 	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FunctionBlock &&  ((FunctionBlock) obj).name.equals(this.name)){
			return true;
		}
		else
			return false;
		
	}
}
