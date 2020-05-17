package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Block implements Command {

    private List<Command> commands = new ArrayList<>();

}
