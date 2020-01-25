package pl.jellysoft.kodbot.resolver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResolverErrorResponse extends ResolverResponse {

    private final String message;

    @Override
    public String getCode() {
        return "ERROR";
    }

}
