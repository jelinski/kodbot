package pl.jellysoft.kodbot.resolver.statistic;

import pl.jellysoft.kodbot.resolver.evaluator.command.*;

import java.util.List;

public class StatisticCounter {

	private StatisticDTO statisticDTO;
	
	public StatisticCounter() {
		this.statisticDTO = new StatisticDTO();
	}
	
	public StatisticDTO countStatistics(List<Command> commands){
		count(commands);
		return statisticDTO;
	}
	
	private void count(List<Command> commands){
		for(Command command : commands){
			
			if(command instanceof MoveCommand){
				incrementMoveCounter();
			}
			else if(command instanceof JumpCommand){
				incrementJumpCounter();
			}
			else if(command instanceof TurnLeftCommand){
				incrementTurnLeftCounter();
			}
			else if(command instanceof TurnRightCommand){
				incrementTurnRightCounter();
			}
			else if(command instanceof AssignWithAdditionCommand){
				incrementAssignWithAdditionCounter();
			}
			else if(command instanceof AssignWithSubtractionCommand){
				incrementAssignWidhSubtractionCounter();
			}
			else if(command instanceof AssignCommand){
				incrementAssignCounter();
			}
			else if(command instanceof IncrementCommand){
				incrementIncrementCounter();
			}
			else if(command instanceof DecrementCommand){
				incrementDecrementCounter();
			}
			else if(command instanceof FunctionCallCommand){
				incrementCalledFunctionCounter();
			}
			else if(command instanceof RepeatBlock){
				incrementRepeatCounter();
				count(((RepeatBlock)command).getCommands());
			}
			else if(command instanceof MainBlock){
				count(((MainBlock)command).getCommands());
			}
			else if(command instanceof FunctionBlock){
				incrementDefinedFunctionCounter();
				count( ((FunctionBlock)command).getCommands());
			}
		}
	}
	
	private void incrementMoveCounter(){
		this.statisticDTO.setMoveCount(this.statisticDTO.getMoveCount()+1);
	}
	
	private void incrementJumpCounter(){
		this.statisticDTO.setJumpCount(this.statisticDTO.getJumpCount()+1);
	}
	
	private void incrementTurnLeftCounter(){
		this.statisticDTO.setTurnLeftCount(this.statisticDTO.getTurnLeftCount()+1);
	}
	
	private void incrementTurnRightCounter(){
		this.statisticDTO.setTurnRightCount(this.statisticDTO.getTurnRightCount()+1);
	}
	
	private void incrementDefinedFunctionCounter(){
		this.statisticDTO.setDefinedFunctionCount(this.statisticDTO.getDefinedFunctionCount()+1);
	}
	
	private void incrementCalledFunctionCounter(){
		this.statisticDTO.setCalledFunctionCount(this.statisticDTO.getCalledFunctionCount()+1);
	}
	
	private void incrementRepeatCounter(){
		this.statisticDTO.setRepeatCount(this.statisticDTO.getRepeatCount()+1);
	}
	
	private void incrementAssignWithAdditionCounter(){
		this.statisticDTO.setAssignWithAdditionCount(this.statisticDTO.getAssignWithAdditionCount()+1);
	}
	
	private void incrementAssignWidhSubtractionCounter(){
		this.statisticDTO.setAssignWithSubtractionCount(this.statisticDTO.getAssignWithSubtractionCount()+1);
	}
	
	private void incrementAssignCounter(){
		this.statisticDTO.setAssignCount(this.statisticDTO.getAssignCount()+1);
	}
	
	private void incrementIncrementCounter(){
		this.statisticDTO.setIncrementCount(this.statisticDTO.getIncrementCount()+1);
	}
	
	private void incrementDecrementCounter(){
		this.statisticDTO.setDecrementCount(this.statisticDTO.getDecrementCount()+1);
	}
}
