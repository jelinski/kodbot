package pl.jellysoft.kodbot.resolver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ResolverOkResponse extends ResolverResponse {

    private final List<ActionType> actions;

    @Override
    public String getCode() {
        return "OK";
    }

}
