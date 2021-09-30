package solver;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

import exceptions.IllegalEquationException;
import symbols.MathSymbol;

public class Solver {

    /**
     * Solves an equation which has been parsed by an Interpreter's parseEquation method
     * @param eq Parsed equation
     * @return Returns the result of the equation
     * @throws IllegalEquationException
     */
    public static BigDecimal solve(ArrayList<MathSymbol> eqrpn) throws IllegalEquationException {
        Stack<BigDecimal> numStack = new Stack<BigDecimal>();
        for (MathSymbol symbol : eqrpn) {
            if (!symbol.isOperator()) {
                numStack.push(symbol.getValue());
            } else {
                switch (symbol.getOperator()) {
                    case ADD:
                        try {
                            add(numStack);
                        } catch (IllegalEquationException e) {
                            throw e;
                        }
                        break;
                    case SUBTRACT:
                        try {
                            subtract(numStack);
                        } catch (IllegalEquationException e) {
                            throw e;
                        }
                        break;
                    case MULTIPLY:
                        try {
                            multiply(numStack);
                        } catch (IllegalEquationException e) {
                            throw e;
                        }
                        break;
                    case DIVIDE:
                        try {
                            divide(numStack);
                        } catch (IllegalEquationException e) {
                            throw e;
                        }
                        break;
                    case MODULUS:
                        try {
                            modulus(numStack);
                        } catch (IllegalEquationException e) {
                            throw e;
                        }
                        break;
                    case EXPONENT:
                        try {
                            exponent(numStack);
                        } catch (IllegalEquationException e) {
                            throw e;
                        }
                        break;
                    default:
                        throw new IllegalEquationException();
                }
            }
        }
        return numStack.pop();
    }

    private static void add(Stack<BigDecimal> numStack) throws IllegalEquationException {
        try {
            BigDecimal x = numStack.pop();
            BigDecimal y = numStack.pop();
            numStack.push(y.add(x));
        } catch (Exception e) {
            throw new IllegalEquationException();
        }
    }

    private static void subtract(Stack<BigDecimal> numStack) throws IllegalEquationException {
        try {
            BigDecimal x = numStack.pop();
            BigDecimal y = numStack.pop();
            numStack.push(y.subtract(x));
        } catch (Exception e) {
            throw new IllegalEquationException();
        }
    }

    private static void multiply(Stack<BigDecimal> numStack) throws IllegalEquationException {
        try {
            BigDecimal x = numStack.pop();
            BigDecimal y = numStack.pop();
            numStack.push(y.multiply(x));
        } catch (Exception e) {
            throw new IllegalEquationException();
        }
    }

    private static void divide(Stack<BigDecimal> numStack) throws IllegalEquationException {
        try {
            BigDecimal x = numStack.pop();
            BigDecimal y = numStack.pop();
            numStack.push(y.divide(x));
        } catch (Exception e) {
            throw new IllegalEquationException();
        }
    }

    private static void modulus(Stack<BigDecimal> numStack) throws IllegalEquationException {
        try {
            BigDecimal x = numStack.pop();
            BigDecimal y = numStack.pop();
            numStack.push(y.remainder(x));
        } catch (Exception e) {
            throw new IllegalEquationException();
        }
    }

    private static void exponent(Stack<BigDecimal> numStack) throws IllegalEquationException {
        try {
            BigDecimal x = numStack.pop();
            BigDecimal y = numStack.pop();
            numStack.push(y.pow(x.intValue()));
        } catch (Exception e) {
            throw new IllegalEquationException();
        }
    }

}
