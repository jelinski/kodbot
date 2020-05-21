package pl.jellysoft.kodbot.resolver.evaluator;

import pl.jellysoft.kodbot.resolver.evaluator.command.Command;
import pl.jellysoft.kodbot.resolver.evaluator.command.FunctionBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.MainBlock;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.MoreCollectors.toOptional;

public class FunctionContainer {

    private final List<FunctionBlock> functions = new ArrayList<>();
    private MainBlock mainFunction = null;

    public void setup(List<Command> commands) throws EvaluatorException {

        for (Command command : commands) {
            if (command instanceof MainBlock) {
                if (mainFunction == null) {
                    mainFunction = ((MainBlock) command);
                } else {
                    throw new EvaluatorException("Może być tylko jedna funkcja main w programie!");
                }
            } else if (command instanceof FunctionBlock) {
                FunctionBlock function = (FunctionBlock) command;
                if (!functions.contains(function)) {
                    functions.add(function);
                } else {
                    throw new EvaluatorException("Funkcja o takiej nazwie " + function.getName() + "  już istnieje");
                }
            } else {
                throw new EvaluatorException("Zewnętrzny element musi być funkcją");
            }
        }
        if (mainFunction == null) {
            throw new EvaluatorException("Nie znaleziono żadnej funkcji main w programie");
        }
    }

    public FunctionBlock getByName(String name) throws EvaluatorException {
        return functions.stream()
                .filter(function -> function.getName().equals(name))
                .collect(toOptional())
                .orElseThrow(() -> new EvaluatorException("Odwolano sie do funkcji, ktora nie jest zdefiniowana"));
    }

    public MainBlock getMainFunction() {
        return mainFunction;
    }

}
