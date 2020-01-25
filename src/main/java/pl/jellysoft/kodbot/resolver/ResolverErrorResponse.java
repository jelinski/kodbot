package pl.jellysoft.kodbot.resolver;

public class ResolverErrorResponse extends ResolverResponse{

    private String message;

	public ResolverErrorResponse(String message) {
		this.message = message;
	}
	
	@Override
	public String getCode() {
		return "ERROR";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
