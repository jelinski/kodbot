package pl.jellysoft.kodbot.resolver.evaluator;

import lombok.Value;

import java.util.List;

@Value
public class EvaluatorResult {

    private final List<ActionType> actions;

}
