package pl.jellysoft.kodbot.resolver;

public abstract class ResolverResponse {

    ResolverResponse() {
        getCode();
    }

    public abstract String getCode();

}
