package symbols;

import java.math.BigDecimal;

import exceptions.IllegalOperatorException;

public class OperatorSymbol implements MathSymbol {

    private final Operator operator;

    public OperatorSymbol(String operator) throws IllegalOperatorException {
        if (operator.equals("+")) {
            this.operator = Operator.ADD;
        } else if (operator.equals("-")) {
            this.operator = Operator.SUBTRACT;
        } else if (operator.equals("*") || operator.equals("x")) {
            this.operator = Operator.MULTIPLY;
        } else if (operator.equals("/")) {
            this.operator = Operator.DIVIDE;
        } else if (operator.equals("%")) {
            this.operator = Operator.MODULUS;
        } else if (operator.equals("^")) {
            this.operator = Operator.EXPONENT;
        } else if (operator.equals("(")) {
            this.operator = Operator.OPEN_PAREN;
        } else if (operator.equals(")")) {
            this.operator = Operator.CLOSE_PAREN;
        } else {
            throw new IllegalOperatorException();
        }
    }

    public OperatorSymbol(Operator operator) {
        this.operator = operator;
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        if (operator.equals(Operator.ADD)) {
            return "+";
        } else if (operator.equals(Operator.SUBTRACT)) {
            return "-";
        } else if (operator.equals(Operator.MULTIPLY)) {
            return "*";
        } else if (operator.equals(Operator.DIVIDE)) {
            return "/";
        } else if (operator.equals(Operator.MODULUS)) {
            return "%";
        } else if (operator.equals(Operator.EXPONENT)) {
            return "^";
        } else if (operator.equals(Operator.OPEN_PAREN)) {
            return "(";
        } else if (operator.equals(Operator.CLOSE_PAREN)) {
            return ")";
        }
        return "";
    }

    /**
     * do not use this method. it has to be here to allow for operators and operands
     * to be in the same list
     */
    @Override
    public BigDecimal getValue() {
        return null;
    }

}
