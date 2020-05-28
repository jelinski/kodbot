package pl.jellysoft.kodbot.resolver.evaluator;

import java.util.HashMap;
import java.util.Map;

public class VariableContainer {

    private static final int VARIABLE_MAX_VALUE = 20;
    private final Map<String, Integer> variables = new HashMap<>();

    public void setVariable(String name, String value) throws EvaluatorException {
        validate(name);

        Integer rightValue;
        if (isNumeric(value)) {
            rightValue = Integer.valueOf(value);
            if (rightValue > VARIABLE_MAX_VALUE) {
                throw new EvaluatorException("Za duza wartosc dla zmiennej: " + name + ". Maksymalna wartość to: " + VARIABLE_MAX_VALUE);
            }
        } else {
            rightValue = getVariable(value);
        }

        variables.put(name, rightValue);
    }

    private Integer getVariable(String name) {
        return variables.getOrDefault(name, 0);
    }

    public Integer getValue(String nameOrValue) {
        if (isNumeric(nameOrValue)) {
            return Integer.valueOf(nameOrValue);
        } else {
            return getVariable(nameOrValue);
        }
    }

    private boolean isNumeric(String val) {
        return val.matches("[0-9]+");
    }

    private void validate(String name) throws EvaluatorException {
        if (isNumeric(name)) {
            throw new EvaluatorException("Cyfry po lewej stronie przypisania");
        }
    }

}
