package Calculator.Classes;

import Calculator.Classes.Core.ComplexNumber;
import Calculator.Interfaces.ICalculationModel;

public class CalculationModel implements ICalculationModel {
    ComplexNumber left;
    ComplexNumber right;
    String operator;
    ComplexNumber lastResult;

    public CalculationModel() {
        left = new ComplexNumber(0, 0);
        right = null;
        operator = "";
        lastResult = null;
    }

    @Override
    public String toString() {
        if (right == null) {
            switch (operator) {
                case "":
                    return String.format("%s", left);
                case "+":
                    return String.format("%s +", left);
                case "-":
                    return String.format("%s -", left);
                case "*":
                    return String.format("%s *", left);
                case "/":
                    return String.format("%s /", left);
            }
        } else {
            switch (operator) {
                case "":
                    return String.format("%s", left);
                case "+":
                    return String.format("%s + %s", left, right);
                case "-":
                    return String.format("%s - %s", left, right);
                case "*":
                    return String.format("%s * %s", left, right);
                case "/":
                    return String.format("%s / %s", left, right);
            }
        }
        throw new IllegalStateException(String.format("Недопустимое значение оператора: %s", operator));
    }

    @Override
    public String represent() {
        return String.format("%s %s %s, %s", left, operator, right, lastResult);
    }

    @Override
    public String getOp() {
        return operator;
    }

    @Override
    public ComplexNumber cancel() {
        left = new ComplexNumber(0, 0);
        right = null;
        operator = "";
        lastResult = null;
        return left;
    }

    @Override
    public ComplexNumber result() {
        if (right == null) {
            if (operator == null) {
                return left;
            } else {
                right = left;
            }
        }
        if (lastResult != null) {
            left = lastResult;
        }
        switch(operator) {
            case "+":
                lastResult = left.add(right);
                return lastResult;
            case "-":
                lastResult = left.sub(right);
                return lastResult;
            case "*":
                lastResult = left.mult(right);
                return lastResult;
            case "/":
                lastResult = left.div(right);
                return lastResult;
        }
        throw new IllegalArgumentException(String.format("Недопустимый математический оператор: %s", operator));
    }

    @Override
    public void setLeft(ComplexNumber left) {
        cancel();
        this.left = left;
    }

    @Override
    public ComplexNumber getLeft() {
        return left;
    }

    @Override
    public void setRight(ComplexNumber right) {
        this.right = right;
    }

    @Override
    public ComplexNumber getRight() {
        return right;
    }

    @Override
    public ComplexNumber handleOp(String op) {
        switch (op) {
            case "c":
                return cancel();
            case "=":
                return result();
            case "+":
            case "-":
            case "*":
            case "/":
                return applyOp(op);
        }
        throw new IllegalArgumentException(String.format("Недопустимый оператор: %s", operator));
    }

    ComplexNumber applyOp(String op) {
        if (right == null || operator.equals("")) {
            operator = op;
            return left;
        }
        // Вычисляем выражение с предыдущим оператором
        switch (operator) {
            case "+":
                lastResult = left.add(right);
                break;
            case "-":
                lastResult = left.sub(right);
                break;
            case "*":
                lastResult = left.mult(right);
                break;
            case "/":
                lastResult = left.div(right);
                break;
        }
        left = lastResult;
        right = null;
        operator = op;
        return lastResult;
    }
}
