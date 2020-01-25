package pl.jellysoft.kodbot.resolver.evaluator;

import pl.jellysoft.kodbot.Constants;

import java.util.ArrayList;

public class VariableContainer {

    private ArrayList<Pair> variables = new ArrayList<>();

    public boolean containsVariable(String name) {
        for (Pair p : variables) {
            if (p.getLeft().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void setVariable(String name, String value) throws EvaluatorException {
        validate(name);

        Integer rightValue = null;
        if (isDigit(value)) {
            rightValue = Integer.valueOf(value);
            if (rightValue > Constants.KODBOT_MAX_VARIABLE_VALUE) {
                throw new EvaluatorException("Za duza wartosc dla zmiennej: " + name + ". Maksymalna wartość to: " + Constants.KODBOT_MAX_VARIABLE_VALUE);
            }
        } else {
            rightValue = getVariable(value);
        }

        for (Pair p : variables) {
            if (p.getLeft().equals(name)) {
                p.setRight(rightValue);
                return;
            }
        }

        variables.add(new Pair(name, rightValue));
    }

    public Integer getVariable(String name) throws EvaluatorException {
        validate(name);
        for (Pair p : variables) {
            if (p.getLeft().equals(name)) {
                return p.getRight();
            }
        }
        return 0;
    }

    public Integer getValue(String nameOrValue) {
        if (isDigit(nameOrValue)) {
            return Integer.valueOf(nameOrValue);
        } else {
            for (Pair p : variables) {
                if (p.getLeft().equals(nameOrValue)) {
                    return p.getRight();
                }
            }
            return 0;
        }
    }

    private boolean isDigit(String val) {
        return val.matches("[0-9]+");
    }

    private void validate(String name) throws EvaluatorException {
        if (isDigit(name))
            throw new EvaluatorException("Cyfry po lewej stronie przypisania");
    }

    private class Pair { // TODO use one from 3rd party lib
        private String left;
        private Integer right;

        public Pair(String left, Integer right) {
            setLeft(left);
            setRight(right);
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public Integer getRight() {
            return right;
        }

        public void setRight(Integer right) {
            this.right = right;
        }
    }

}