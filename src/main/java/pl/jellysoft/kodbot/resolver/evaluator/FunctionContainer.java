package pl.jellysoft.kodbot.resolver.evaluator;

import pl.jellysoft.kodbot.resolver.evaluator.command.Command;
import pl.jellysoft.kodbot.resolver.evaluator.command.FunctionBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.MainBlock;

import java.util.ArrayList;
import java.util.List;

public class FunctionContainer {

    private MainBlock mainFunction;
    private final List<FunctionBlock> functions;

    public FunctionContainer() {
        functions = new ArrayList<>();
    }

    public void setup(List<Command> commands) throws EvaluatorException {
        boolean mainFunctionFound = false;
        // Iteracja po zewnetrznych elementach bez rekurencji, poniewaz funkcjie nie moga byc definiowane wewnatrz innych blokow
        for (Command command : commands) {
            if (command instanceof MainBlock) {
                if (!mainFunctionFound) {
                    setMainFunction((MainBlock) command);
                    mainFunctionFound = true;
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
        if (!mainFunctionFound) {
            throw new EvaluatorException("Nie znaleziono żadnej funkcji main w programie");
        }
    }

    public FunctionBlock getByName(String name) throws EvaluatorException {
        for (int i = 0; i < functions.size(); i++) {
            FunctionBlock f = functions.get(i);
            if (f.getName().equals(name)) {
                return f;
            }
        }
        throw new EvaluatorException("Odwolano sie do funkcji, ktora nie jest zdefiniowana");
    }

    public MainBlock getMainFunction() {
        return this.mainFunction;
    }

    private void setMainFunction(MainBlock mainFunction) {
        this.mainFunction = mainFunction;
    }

}
