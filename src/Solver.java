import java.util.ArrayList;

import exceptions.DivideByZeroException;
import exceptions.IllegalFactorialException;

public class Solver {

    /**
     * Solves an equation which has been parsed by an Interpreter's parseEquation method
     * @param eq parsed equation
     * @param startIndex index of the equation to start at (inclusive)
     * @param endIndex index of the equation to end at (exclusive)
     * @return returns the result of the equation
     * @throws DivideByZeroException
     */
    public String solve(ArrayList<String> eq, int startIndex, int endIndex) throws DivideByZeroException, IllegalFactorialException {
        // parentheses
        for (int i = startIndex; i < endIndex; i++) {
            if (eq.get(i).equals("(")) {
                int numOpening = 0;
                for (int j = i + 1; j < endIndex; j++) {
                    if (eq.get(j).equals("(")) {
                        numOpening++;
                    } else if (eq.get(j).equals(")")) {
                        if (numOpening > 0) {
                            numOpening--;
                        } else {
                            try {
                                solve(eq, i + 1, j);
                                eq.set(i, eq.get(i + 1));
                                eq.remove(i + 2);
                                eq.remove(i + 1);
                                endIndex -= j - i;
                                break;
                            } catch (DivideByZeroException e) {
                                throw e;
                            }
                        }
                    }
                }
            }
        }
        // exponents and factorials
        for (int i = startIndex; i < endIndex - 1; i++) {
            if (eq.get(i).equals("^")) {
                eq.set(i, exponent(eq.get(i - 1), eq.get(i + 1)));
                eq.remove(i + 1);
                eq.remove(i - 1);
                endIndex -= 2;
                i--;
            } else if (eq.get(i + 1).equals("!")) {
                try {
                    eq.set(i, factorial(eq.get(i)));
                    eq.remove(i + 1);
                    endIndex--;
                } catch (IllegalFactorialException e) {
                    throw e;
                }
            }
        }
        // multiply & divide
        for (int i = startIndex; i < endIndex - 1; i++) {
            if (eq.get(i).equals("*")) {
                eq.set(i, multiply(eq.get(i - 1), eq.get(i + 1)));
                eq.remove(i + 1);
                eq.remove(i - 1);
                endIndex -= 2;
                i--;
            } else if (eq.get(i).equals("/")) {
                try {
                    eq.set(i, divide(eq.get(i - 1), eq.get(i + 1)));
                    eq.remove(i + 1);
                    eq.remove(i - 1);
                    endIndex -= 2;
                    i--;
                } catch (DivideByZeroException e) {
                    throw e;
                }
            }
        }
        // add
        for (int i = startIndex; i < endIndex - 1; i++) {
            if (eq.get(i).equals("+")) {
                eq.set(i, add(eq.get(i - 1), eq.get(i + 1)));
                eq.remove(i + 1);
                eq.remove(i - 1);
                endIndex -= 2;
                i--;
            }
        }
        return eq.get(startIndex);
    }

    private String exponent(String a, String b) {
        return Double.toString(Math.pow(Double.parseDouble(a), Double.parseDouble(b)));
    }

    private String factorial(String a) throws IllegalFactorialException {
        double da = Double.parseDouble(a);
        if (da % 1 != 0 || da < 0) {
            throw new IllegalFactorialException("Error: Illegal Factorial");
        }
        if (da == 0) {
            return Integer.toString(1);
        }
        try {
            return Double.toString(da * Double.parseDouble(factorial(Double.toString(da - 1))));
        } catch (IllegalFactorialException e) {
            throw e;
        }
    }

    private String multiply(String a, String b) {
        return Double.toString(Double.parseDouble(a) * Double.parseDouble(b));
    }

    private String divide(String a, String b) throws DivideByZeroException {
        if (Double.parseDouble(b) == 0) {
            throw new DivideByZeroException("Error: Divide By Zero");
        }
        return Double.toString(Double.parseDouble(a) / Double.parseDouble(b));
    }

    private String add(String a, String b) {
        return Double.toString(Double.parseDouble(a) + Double.parseDouble(b));
    }

}
