package pl.jellysoft.kodbot.resolver.evaluator.command;

import java.util.ArrayList;
import java.util.List;

public abstract class Block extends Command {
	
	private List<Command> commands;
	
	public Block() {
		this.commands = new ArrayList<Command>();
	}
	
	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}
	
}
