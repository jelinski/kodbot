package pl.jellysoft.kodbot.resolver.parser;

import lombok.Value;
import pl.jellysoft.kodbot.resolver.evaluator.command.Command;

import java.util.List;

@Value
public class ParserResult {

    private final int commandCounter;
    private final List<Command> commands;

}
