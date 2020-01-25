package pl.jellysoft.kodbot.resolver.parser;

import pl.jellysoft.kodbot.resolver.evaluator.command.Command;

import java.util.List;

public abstract class Parser {

	public abstract ParserResult parse(String input) throws ParserException;
	
	public class ParserResult{
		private int commandCounter;
		private List<Command> commands;
		
		public int getCommandCounter() {
			return commandCounter;
		}
		public void setCommandCounter(int commandCounter) {
			this.commandCounter = commandCounter;
		}
		public List<Command> getCommands() {
			return commands;
		}
		public void setCommands(List<Command> commands) {
			this.commands = commands;
		}
		public ParserResult(int commandCounter, List<Command> commands) {
			this.commandCounter = commandCounter;
			this.commands = commands;
		}
		
		
	}
}
