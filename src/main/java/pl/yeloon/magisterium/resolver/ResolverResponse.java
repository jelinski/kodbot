package pl.yeloon.magisterium.resolver;

public abstract class ResolverResponse {

    ResolverResponse() {
        getCode();
	}

	public abstract String getCode();

}
