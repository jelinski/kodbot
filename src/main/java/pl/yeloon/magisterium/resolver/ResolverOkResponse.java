package pl.yeloon.magisterium.resolver;

import java.util.List;

import pl.yeloon.magisterium.resolver.evaluator.ActionType;

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
