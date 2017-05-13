package pl.yeloon.magisterium.resolver;

public abstract class ResolverResponse {
	private String code;

	public ResolverResponse() {
		this.code = getCode();
	}

	public abstract String getCode();

}
