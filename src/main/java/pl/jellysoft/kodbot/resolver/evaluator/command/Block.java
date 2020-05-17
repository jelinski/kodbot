package pl.jellysoft.kodbot.resolver.evaluator.command;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Block implements Command {

    private List<Command> commands = new ArrayList<>();

}
