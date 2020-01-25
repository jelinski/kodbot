package pl.jellysoft.kodbot.resolver;

import pl.jellysoft.kodbot.resolver.evaluator.ActionType;

import java.util.List;

public class ResolverOkResponse extends ResolverResponse {

	private List<ActionType> actions;
	
	public ResolverOkResponse(List<ActionType> actions) {
		setActions(actions);
	}
	
	@Override
	public String getCode() {
		return "OK";
	}
	
	public List<ActionType> getActions() {
		return actions;
	}

	public void setActions(List<ActionType> actions) {
		this.actions = actions;
	}

}
